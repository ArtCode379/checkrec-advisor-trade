package checkrec.advisor.trade.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import checkrec.advisor.trade.data.model.ServiceModel

class ServiceRepository {

    private val services = listOf(
        ServiceModel(
            id = 1,
            name = "Business Strategy Audit",
            description = "A comprehensive review of your current business strategy, identifying gaps, strengths, and opportunities for sustainable competitive advantage.",
            price = 2800.0,
            imageUrl = "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?w=800",
            category = "Strategic Planning",
            durationMinutes = 120,
            features = listOf("Strategy review", "Competitive analysis", "Gap identification", "Opportunity mapping", "Action plan"),
            availableSlots = listOf("09:00", "11:00", "14:00", "16:00")
        ),
        ServiceModel(
            id = 2,
            name = "Process Optimisation Consulting",
            description = "Systematic review and redesign of your core business processes to eliminate waste, reduce costs, and improve operational efficiency.",
            price = 2200.0,
            imageUrl = "https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=800",
            category = "Operations",
            durationMinutes = 90,
            features = listOf("Process mapping", "Waste analysis", "Lean methodology", "SOP creation", "Training plan"),
            availableSlots = listOf("09:00", "12:00", "15:00")
        ),
        ServiceModel(
            id = 3,
            name = "Organisational Development",
            description = "Expert advisory on restructuring your organisation's hierarchy, roles, and responsibilities to align with business goals and improve performance.",
            price = 3500.0,
            imageUrl = "https://images.unsplash.com/photo-1552664730-d307ca884978?w=800",
            category = "HR & Organisation",
            durationMinutes = 150,
            features = listOf("Org review", "Role clarity", "Team alignment", "Culture assessment", "Change roadmap"),
            availableSlots = listOf("10:00", "13:00", "16:00")
        ),
        ServiceModel(
            id = 4,
            name = "Executive Leadership Coaching",
            description = "One-to-one coaching for executives and senior managers to develop leadership capabilities, strategic thinking, and high-performance team management.",
            price = 4000.0,
            imageUrl = "https://images.unsplash.com/photo-1517048676732-d65bc937f952?w=800",
            category = "Leadership",
            durationMinutes = 90,
            features = listOf("Leadership assessment", "Strengths analysis", "1:1 sessions", "Goal setting", "Progress tracking"),
            availableSlots = listOf("09:00", "14:00", "17:00")
        ),
        ServiceModel(
            id = 5,
            name = "Change Management Programme",
            description = "Structured consulting to guide your organisation through transformational change, ensuring staff adoption, minimal disruption, and lasting results.",
            price = 3200.0,
            imageUrl = "https://images.unsplash.com/photo-1556761175-4b46a572b786?w=800",
            category = "Change Management",
            durationMinutes = 120,
            features = listOf("Stakeholder mapping", "Change impact analysis", "Communication plan", "Resistance management", "Post-change review"),
            availableSlots = listOf("10:00", "13:00", "15:00")
        ),
        ServiceModel(
            id = 6,
            name = "Market Research & Analysis",
            description = "Deep-dive market research to identify trends, customer segments, and competitive dynamics to inform your product and go-to-market strategy.",
            price = 1800.0,
            imageUrl = "https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800",
            category = "Strategic Planning",
            durationMinutes = 90,
            features = listOf("Market sizing", "Trend analysis", "Customer profiling", "Competitor benchmarking", "Insight report"),
            availableSlots = listOf("09:00", "11:00", "14:00")
        ),
        ServiceModel(
            id = 7,
            name = "Performance Management Systems",
            description = "Design and implementation of KPI frameworks, appraisal systems, and performance dashboards to drive accountability across all levels.",
            price = 2000.0,
            imageUrl = "https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800",
            category = "HR & Organisation",
            durationMinutes = 90,
            features = listOf("KPI framework", "Review cadence", "Dashboard setup", "Appraisal design", "Manager training"),
            availableSlots = listOf("11:00", "14:00", "16:00")
        ),
        ServiceModel(
            id = 8,
            name = "Digital Readiness Assessment",
            description = "Evaluate your organisation's readiness for digital transformation, identify technology gaps, and build a prioritised roadmap for adoption.",
            price = 2600.0,
            imageUrl = "https://images.unsplash.com/photo-1485827404703-89b55fcc595e?w=800",
            category = "Operations",
            durationMinutes = 120,
            features = listOf("Digital maturity audit", "Technology review", "Data strategy", "Vendor assessment", "Transformation roadmap"),
            availableSlots = listOf("09:00", "12:00", "15:00")
        ),
        ServiceModel(
            id = 9,
            name = "Board Advisory Services",
            description = "Senior-level advisory for boards and governance committees, covering strategic direction, risk oversight, and stakeholder relations.",
            price = 5000.0,
            imageUrl = "https://images.unsplash.com/photo-1521791136064-7986c2920216?w=800",
            category = "Leadership",
            durationMinutes = 180,
            features = listOf("Board effectiveness", "Risk governance", "Strategic oversight", "NED advisory", "Compliance guidance"),
            availableSlots = listOf("10:00", "14:00")
        ),
        ServiceModel(
            id = 10,
            name = "Operational Resilience Planning",
            description = "Build resilience into your business operations by identifying vulnerabilities, developing contingency plans, and stress-testing critical processes.",
            price = 2400.0,
            imageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=800",
            category = "Operations",
            durationMinutes = 120,
            features = listOf("Risk identification", "Contingency planning", "Business continuity", "Scenario testing", "Recovery protocols"),
            availableSlots = listOf("09:00", "12:00", "15:00")
        )
    )

    fun observeAll(): Flow<List<ServiceModel>> = flowOf(services)
    fun observeById(id: Int): Flow<ServiceModel?> = flowOf(services.find { it.id == id })
    fun getById(id: Int): ServiceModel? = services.find { it.id == id }
    suspend fun getAll(): List<ServiceModel> = services
}
