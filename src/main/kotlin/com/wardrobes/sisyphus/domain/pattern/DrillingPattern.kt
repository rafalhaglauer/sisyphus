package com.wardrobes.sisyphus.domain.pattern

import com.wardrobes.sisyphus.domain.drilling.DrillingDestination
import com.wardrobes.sisyphus.model.Drilling
import com.wardrobes.sisyphus.model.Element
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
data class DrillingPattern(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    var name: String = "",
    @OneToOne(cascade = [(CascadeType.ALL)]) var positionX: PositionPattern = PositionPattern(),
    @OneToOne(cascade = [(CascadeType.ALL)]) var positionY: PositionPattern = PositionPattern(),
    var destination: DrillingDestination = DrillingDestination.UNKNOWN,
    @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) var element: ElementPattern = ElementPattern()
) {

    fun asDrilling(element: Element): Drilling = Drilling(
        xPosition = positionX.calculate(element),
        yPosition = positionY.calculate(element),
        depth = getDepth(destination),
        diameter = getDiameter(destination)
    )

    private fun getDepth(destination: DrillingDestination): Float = 12F // TODO

    private fun getDiameter(destination: DrillingDestination): Float = 8F // TODO

}