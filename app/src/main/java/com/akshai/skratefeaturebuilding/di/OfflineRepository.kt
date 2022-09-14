package com.akshai.skratefeaturebuilding.di

import com.akshai.skratefeaturebuilding.reponse.DashboardStats
import com.akshai.skratefeaturebuilding.reponse.JobPostingsItem
import com.akshai.skratefeaturebuilding.reponse.MockApiResponse
import com.akshai.skratefeaturebuilding.reponse.UpcomingSessionsItem
import com.akshai.skratefeaturebuilding.utils.DataHandler
import retrofit2.Response
import javax.inject.Inject

class OfflineRepository @Inject constructor() : BaseRepository {
    override suspend fun getSampleData(): Response<MockApiResponse> {
        return Response.success(
            MockApiResponse(
                fullName = "Akshai V Sunil",
                dashboardStats = DashboardStats(
                    profileViews = 12,
                    jobsApplied = 33,
                    mentorshipSessions = 11,
                    skillsVerified = 7
                ),
                upcomingSessions = listOf(
                    UpcomingSessionsItem(
                        mentorName = "Test",
                        date = "23/11/2022",
                        timings = "12:30 - 15:00",
                        sessionType = "Review"
                    ),
                    UpcomingSessionsItem(
                        mentorName = "Test2",
                        date = "2/11/2022",
                        timings = "12:30 - 15:00",
                        sessionType = "Dev"
                    ),
                ),
                jobPostings = listOf(
                    JobPostingsItem(
                        role = "IOS Developer",
                        organizationName = "Amazon",
                        location = "Remote",
                        datePosted = "12/12/2021"
                    ),
                    JobPostingsItem(
                        role = "Android Developer",
                        organizationName = "TCS",
                        location = "USA",
                        datePosted = "12/12/2021"
                    )
                )

            )
        )
    }
}