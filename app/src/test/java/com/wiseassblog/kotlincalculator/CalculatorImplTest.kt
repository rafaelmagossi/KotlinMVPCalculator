package com.wiseassblog.kotlincalculator

import com.wiseassblog.kotlincalculator.data.CalculatorImpl
import com.wiseassblog.kotlincalculator.data.datamodel.Operand
import com.wiseassblog.kotlincalculator.data.datamodel.Operator
import com.wiseassblog.kotlincalculator.viewmodel.CalculatorVM
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by R_KAY on 1/6/2018.
 */
class CalculatorImplTest {
    private val calc = CalculatorImpl()

    val SIMPLE_EXPRESSION = "2+2"
    val SIMPLE_ANSWER = "4.0"

    val COMPLEX_EXPRESSION = "2+2-1*3+4"
    val COMPLEX_ANSWER = "5.0"
    val OPERANDS = listOf<Operand>(
            Operand("2"),
            Operand("2"),
            Operand("1"),
            Operand("3"),
            Operand("4")
    )
    val OPERATORS = listOf<Operator>(
            Operator("+"),
            Operator("-"),
            Operator("*"),
            Operator("+")
    )
    val INVALID_EXPRESSION_ONE = "2+"
    val INVALID_EXPRESSION_TWO = "+2"
    val INVALID_EXPRESSION_THREE = "2+-"
    val INVALID_ANSWER = "Error: Invalid Expression"

    /**
     * Get operands of current expression
     */
    @Test
    fun getOperands() {
        val operands: List<Operand> = calc.getOperands(COMPLEX_EXPRESSION)

        assertTrue(operands == OPERANDS)
    }

    @Test
    fun getOperators() {
        val operators: List<Operator> = calc.getOperators(COMPLEX_EXPRESSION)

        assertTrue(operators == OPERATORS)
    }

//    @Test
//    fun evaluatePair(){
//        calc.evaluatePair(OPERANDS[0], OPERATORS[0], OPERANDS[1], OPERATORS[1])
//    }


    @Test
    fun onEvaluateValidSimpleExpression() {
        val subscriber = TestSubscriber<CalculatorVM>()

        calc.evaluateExpression(SIMPLE_EXPRESSION).subscribeWith(subscriber)

        assertTrue(subscriber.values()[0].result == SIMPLE_ANSWER)
    }
//
    @Test
    fun onEvaluateValidComplexExpression() {
        var subscriber = TestSubscriber<CalculatorVM>()

        calc.evaluateExpression(COMPLEX_EXPRESSION).subscribeWith(subscriber)

    assertTrue(subscriber.values()[0].result == COMPLEX_ANSWER)

}
//
//    @Test
//    fun onEvaluateInvalidSimpleExpression() {
//        var subscriber = TestSubscriber<CalculatorVM>()
//
//        calc.evaluateExpression(INVALID_EXPRESSION).subscribeWith(subscriber)
//
//        subscriber.assertValue(CalculatorVM.createSuccessModel(INVALID_ANSWER))
//    }
}





