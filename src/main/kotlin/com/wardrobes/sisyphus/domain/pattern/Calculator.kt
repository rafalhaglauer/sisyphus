package com.wardrobes.sisyphus.domain.pattern

import javax.script.ScriptEngineManager

object Calculator {

    private val calculateEngine = ScriptEngineManager().getEngineByName("JavaScript")

    fun calculate(rawMathExpression: String): Number = calculateEngine.eval(rawMathExpression) as Number

}