package com.wardrobes.sisyphus.domain.pattern

import com.wardrobes.sisyphus.domain.wardrobe.Wardrobe
import com.wardrobes.sisyphus.model.Element
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
data class ElementPattern(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0L,
    var name: String = "",
    @OneToOne(cascade = [(CascadeType.ALL)]) var length: LengthPattern = LengthPattern(),
    @OneToOne(cascade = [(CascadeType.ALL)]) var width: LengthPattern = LengthPattern(),
    var height: Int = 0,
    @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) var wardrobe: WardrobePattern = WardrobePattern()
) {

    fun asElement(wardrobe: Wardrobe): Element = Element(
        name = name,
        length = length.calculate(wardrobe),
        width = width.calculate(wardrobe),
        height = height,
        wardrobe = wardrobe
    )

}