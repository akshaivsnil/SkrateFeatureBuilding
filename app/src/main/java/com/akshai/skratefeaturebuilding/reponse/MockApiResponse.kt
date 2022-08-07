package com.akshai.skratefeaturebuilding.reponse

import com.google.gson.annotations.SerializedName

data class MockApiResponse(

	@field:SerializedName("dashboard_stats")
	val dashboardStats: DashboardStats? = null,

	@field:SerializedName("job_postings")
	val jobPostings: List<JobPostingsItem?>? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("upcoming_sessions")
	val upcomingSessions: List<UpcomingSessionsItem?>? = null
)

data class JobPostingsItem(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("date_posted")
	val datePosted: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("organization_name")
	val organizationName: String? = null
)

data class UpcomingSessionsItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("timings")
	val timings: String? = null,

	@field:SerializedName("session_type")
	val sessionType: String? = null,

	@field:SerializedName("mentor_name")
	val mentorName: String? = null,

	val role: String? = null,

	val color : Int ? = null

)

data class DashboardStats(

	@field:SerializedName("skills_verified")
	val skillsVerified: Int? = null,

	@field:SerializedName("profile_views")
	val profileViews: Int? = null,

	@field:SerializedName("mentorship_sessions")
	val mentorshipSessions: Int? = null,

	@field:SerializedName("jobs_applied")
	val jobsApplied: Int? = null
)
