package com.akshai.skratefeaturebuilding.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.akshai.skratefeaturebuilding.MyViewModel
import com.akshai.skratefeaturebuilding.R
import com.akshai.skratefeaturebuilding.adaptor.NewJobsAdaptor
import com.akshai.skratefeaturebuilding.adaptor.OverViewAdaptor
import com.akshai.skratefeaturebuilding.adaptor.UpCommingSessionAdaptor
import com.akshai.skratefeaturebuilding.databinding.FragmentHomeBinding
import com.akshai.skratefeaturebuilding.model.OverViewModel
import com.akshai.skratefeaturebuilding.reponse.DashboardStats
import com.akshai.skratefeaturebuilding.reponse.JobPostingsItem
import com.akshai.skratefeaturebuilding.reponse.UpcomingSessionsItem
import com.akshai.skratefeaturebuilding.utils.DataHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentHomeBinding

    val viewModel: MyViewModel by viewModels()

    var shuffle: Boolean = false

    @Inject
    lateinit var overViewAdapter: OverViewAdaptor

    @Inject
    lateinit var upCommingSessionAdaptor: UpCommingSessionAdaptor

    @Inject
    lateinit var newJobAdapter: NewJobsAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        viewModel.moclHeadlines.observe(viewLifecycleOwner) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                    dataHandler.data?.let {
                        setOverviewData(it.dashboardStats)
                        setSessionData(it.upcomingSessions)
                        setNewJobData(it.jobPostings)
                    }

                    //binding.progressBar.visibility = View.GONE
                    //newsAdapter.differ.submitList(dataHandler.data?.articles)
                }
                is DataHandler.ERROR -> {
                    //binding.progressBar.visibility = View.GONE
                    //LogData("onViewCreated: ERROR " + dataHandler.message)
                }
                is DataHandler.LOADING -> {
                    //binding.progressBar.visibility = View.VISIBLE
                    // LogData("onViewCreated: LOADING..")

                }
            }

        }
        viewModel.getMockApi()
    }


    private fun init() {
        binding.apply {
            overView.overViewRecycler.adapter = overViewAdapter
            newJobs.newJobsRecycler.adapter = newJobAdapter
            upComming.sessionRecycler.adapter = upCommingSessionAdaptor


            homeLayout.setOnClickListener {
                shuffle = false
                shuffleSelect.visibility = View.INVISIBLE
                homeSelect.visibility = View.VISIBLE
                viewModel.getMockApi()

            }

            shuffleLayout.setOnClickListener {

                homeSelect.visibility = View.INVISIBLE
                shuffleSelect.visibility = View.VISIBLE
                shuffle = true
                viewModel.getMockApi()
            }

            profile.setOnClickListener {
                shuffle = false
                shuffleSelect.visibility = View.INVISIBLE
                homeSelect.visibility = View.VISIBLE
                viewModel.getMockApi()
            }
        }
    }

    private fun setOverviewData(dashboardStats: DashboardStats?) {
        val overViewList = arrayListOf<OverViewModel>()

        dashboardStats?.let {
            overViewList.add(
                OverViewModel(
                    name = "Profile View",
                    count = it.profileViews.toString()
                )
            )

            overViewList.add(
                OverViewModel(
                    name = "Mentorship Sessions",
                    count = it.mentorshipSessions.toString()
                )
            )

            overViewList.add(
                OverViewModel(
                    name = "Jobs Applied",
                    count = it.jobsApplied.toString()
                )
            )

            overViewList.add(
                OverViewModel(
                    name = "Skills Verified",
                    count = it.skillsVerified.toString()
                )
            )
        }
        if (shuffle)
            overViewAdapter.overViewList = overViewList.shuffled()
        else
            overViewAdapter.overViewList = overViewList
    }


    private fun setSessionData(upcomingSessions: List<UpcomingSessionsItem?>?) {
        val list = arrayListOf<UpcomingSessionsItem>()
        upcomingSessions?.forEachIndexed { index, session ->
            if (session != null) {
                list.add(
                    UpcomingSessionsItem(
                        date = session.date,
                        sessionType = session.sessionType,
                        mentorName = session.mentorName,
                        timings = session.timings,
                        role = if (index % 2 == 0) "Flutter" else "Django",
                        color = if (index % 2 == 0) R.color.one else R.color.two
                    )
                )
            }
        }
        if (shuffle)
            upCommingSessionAdaptor.list = list.shuffled()
        else
            upCommingSessionAdaptor.list = list

    }


    private fun setNewJobData(jobPostings: List<JobPostingsItem?>?) {
        val jobItemList = arrayListOf<JobPostingsItem>()
        jobPostings?.forEach { jobPostingsItem ->
            if (jobPostingsItem != null) {
                jobItemList.add(
                    JobPostingsItem(
                        role = getSplitedName(jobPostingsItem.role),
                        datePosted = jobPostingsItem.datePosted,
                        location = jobPostingsItem.location,
                        organizationName = jobPostingsItem.organizationName
                    )
                )
            }
        }
        if (shuffle)
            newJobAdapter.list = jobItemList.shuffled()
        else
            newJobAdapter.list = jobItemList
    }

    private fun getSplitedName(role: String?): String {
        val split = role!!.trim().split(" ")
        return if (split.size > 1) {
            split[0] + "\n" + split[1]
        } else {
            role
        }
    }
}