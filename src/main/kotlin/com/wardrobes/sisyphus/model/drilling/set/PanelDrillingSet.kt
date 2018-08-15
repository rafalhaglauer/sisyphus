//package com.wardrobes.sisyphus.model.drilling.set
//
//object HangingWardrobePanelDrillingSet : RelativeDrillingSet {
//
//    override fun getDrillingGroup(): List<RelativeDrilling> =
//            listOf(RelativeDrilling(xPosition = RelativePosition(offset = 40f), drillingType = DowelDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(offset = 60f), drillingType = ConfirmatScrewDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(percentageOffset = 0.5f), drillingType = DowelDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(offset = 60f, referenceType = ReferenceType.END), drillingType = ConfirmatScrewDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(offset = 40f, referenceType = ReferenceType.END), drillingType = DowelDrilling))
//
//}
//
//object StandingWardrobeBottomPanelDrillingSet : RelativeDrillingSet {
//
//    override fun getDrillingGroup(): List<RelativeDrilling> =
//            listOf(RelativeDrilling(xPosition = RelativePosition(offset = 40f), drillingType = DowelDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(offset = 60f), drillingType = ConfirmatScrewDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(percentageOffset = 0.5f), drillingType = ConfirmatScrewDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(offset = 60f, referenceType = ReferenceType.END), drillingType = ConfirmatScrewDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(offset = 40f, referenceType = ReferenceType.END), drillingType = DowelDrilling))
//
//}
//
//object StandingWardrobeTopPanelDrillingSet : RelativeDrillingSet {
//
//    override fun getDrillingGroup(): List<RelativeDrilling> =
//            listOf(RelativeDrilling(xPosition = RelativePosition(offset = 25f), drillingType = DowelDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(percentageOffset = 0.5f), drillingType = ConfirmatScrewDrilling),
//                    RelativeDrilling(xPosition = RelativePosition(offset = 25f, referenceType = ReferenceType.END), drillingType = DowelDrilling))
//
//}