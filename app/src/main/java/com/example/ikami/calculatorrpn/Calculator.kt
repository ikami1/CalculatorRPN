package com.example.ikami.calculatorrpn

import android.icu.math.BigDecimal
import kotlin.collections.ArrayList
import java.util.*

class Calculator {
    private val stack: Deque<BigDecimal> = ArrayDeque<BigDecimal>()
    private var precision: Int = 2

    fun getPrecision(): Int = this.precision

    fun isEmpty(): Boolean = stack.isEmpty()

    fun stackSize(): Int = stack.size

    fun pushOnTop(number: BigDecimal){
        stack.push(number)
    }

    fun getTop4(): ArrayList<BigDecimal> {
        val numbers: ArrayList<BigDecimal> = ArrayList()
        if(stack.isNotEmpty()){
            for (i in 0..3) {
                try {
                    numbers.add(stack.pop())
                }
                catch (e: NoSuchElementException){
                    break
                }
            }
            for (i in numbers.size-1 downTo 0){
                stack.push(numbers[i])
            }
        }

        for (i in numbers.size-1..3){
            numbers.add(BigDecimal(0))
        }

        return numbers
    }

    fun powTop2(){
        val exponent = stack.pop()
        val topNumber = stack.pop()
        stack.push(topNumber.pow(exponent))
    }

    fun subtractTop2(){
        val topNumber = stack.pop()
        val secondNumber = stack.pop()
        stack.push(secondNumber.subtract(topNumber))
    }

    fun addTop2(){
        val topNumber = stack.pop()
        val secondNumber = stack.pop()
        stack.push(secondNumber.add(topNumber))
    }

    fun changeSign(){
        val topnumber = stack.pop()
        stack.push(topnumber.multiply(BigDecimal(-1)))
    }

    fun divideTop2(){
        val topNumber = stack.pop()
        val secondNumber = stack.pop()
        if (topNumber != BigDecimal(0)){
            stack.push(secondNumber.divide(topNumber,precision, BigDecimal.ROUND_HALF_UP))
        }
        else{
            stack.push(secondNumber)
            stack.push(topNumber)
        }
    }

    fun setPrecision(precision: Int){
        this.precision = precision
        stack.forEach { number -> kotlin.run {
            if (number != number.setScale(0))
                number.setScale(precision)
        } }
    }

    fun multiplyTop2(){
        val topNumber = stack.pop()
        val secondNumber = stack.pop()
        stack.push(secondNumber.multiply(topNumber))
    }

    fun dropTopNumber(){
        stack.pop()
    }

    fun swapTop2(){
        val topNumber = stack.pop()
        val secondNumber = stack.pop()
        stack.push(topNumber)
        stack.push(secondNumber)
    }
}