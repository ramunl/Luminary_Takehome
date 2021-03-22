package cvdevelopers.githubstalker

import cvdevelopers.githubstalker.data.model.*

val MockedPage1 = ResultsPage( 1,PageInfo(1,3,"82c7ccd5cc1dc533", "1.3"),
        listOf(
                RandomUser(0,
                        Name(0, "roman","lebedenko", ""),
                        Picture(0, "","","")),
                RandomUser(0,
                        Name(0, "roman1","lebedenko1", ""),
                        Picture(0, "","","")),
                RandomUser(0,
                        Name(0, "roman2","lebedenko2", ""),
                        Picture(0, "","",""))
        ))

val MockedPage1_1 = ResultsPage( 1,PageInfo(1,6,"82c7ccd5cc1dc533", "1.3"),
        listOf(
                RandomUser(0,
                        Name(0, "anton","antonov", ""),
                        Picture(0, "","","")),
                RandomUser(0,
                        Name(0, "anton1","antonov1", ""),
                        Picture(0, "","","")),
                RandomUser(0,
                        Name(0, "anton2","antonov2", ""),
                        Picture(0, "","","")),
                RandomUser(0,
                        Name(0, "anton3","antonov3", ""),
                        Picture(0, "","","")),
                RandomUser(0,
                        Name(0, "anton4","antonov4", ""),
                        Picture(0, "","","")),
                RandomUser(0,
                        Name(0, "anton5","antonov5", ""),
                        Picture(0, "","",""))
        ))

val MockedPage2 = ResultsPage( 2, PageInfo(2,2,"82c7ccd5cc1dc533", "1.3"),
        listOf(
                RandomUser(0,
                        Name(0, "roman","lebedenko", ""),
                        Picture(0, "","","")),
                RandomUser(0,
                        Name(0, "roman1","lebedenko1", ""),
                        Picture(0, "","",""))
        ))