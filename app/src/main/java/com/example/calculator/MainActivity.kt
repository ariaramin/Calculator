package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import javax.xml.xpath.XPathExpression

class MainActivity : AppCompatActivity() {

    private var input: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        input?.showSoftInputOnFocus = false

        val zeroBtn = findViewById<Button>(R.id.zeroBtn)
        zeroBtn.setOnClickListener {
            updateText("0")
        }

        val oneBtn = findViewById<Button>(R.id.oneBtn)
        oneBtn.setOnClickListener {
            updateText("1")
        }

        val twoBtn = findViewById<Button>(R.id.twoBtn)
        twoBtn.setOnClickListener {
            updateText("2")
        }

        val threeBtn = findViewById<Button>(R.id.threeBtn)
        threeBtn.setOnClickListener {
            updateText("3")
        }

        val fourBtn = findViewById<Button>(R.id.fourBtn)
        fourBtn.setOnClickListener {
            updateText("4")
        }

        val fiveBtn = findViewById<Button>(R.id.fiveBtn)
        fiveBtn.setOnClickListener {
            updateText("5")
        }

        val sixBtn = findViewById<Button>(R.id.sixBtn)
        sixBtn.setOnClickListener {
            updateText("6")
        }

        val sevenBtn = findViewById<Button>(R.id.sevenBtn)
        sevenBtn.setOnClickListener {
            updateText("7")
        }

        val eightBtn = findViewById<Button>(R.id.eightBtn)
        eightBtn.setOnClickListener {
            updateText("8")
        }

        val nineBtn = findViewById<Button>(R.id.nineBtn)
        nineBtn.setOnClickListener {
            updateText("9")
        }

        val plusBtn = findViewById<Button>(R.id.plusBtn)
        plusBtn.setOnClickListener {
            updateText("+")
        }

        val minusBtn = findViewById<Button>(R.id.minusBtn)
        minusBtn.setOnClickListener {
            updateText("-")
        }

        val multiplicationBtn = findViewById<Button>(R.id.multiplicationBtn)
        multiplicationBtn.setOnClickListener {
            updateText("×")
        }

        val divisionBtn = findViewById<Button>(R.id.divisionBtn)
        divisionBtn.setOnClickListener {
            updateText("÷")
        }

        val remainderBtn = findViewById<Button>(R.id.remainderBtn)
        remainderBtn.setOnClickListener {
            updateText("%")
        }

        val decimalBtn = findViewById<Button>(R.id.decimalBtn)
        decimalBtn.setOnClickListener {
            updateText(".")
        }

        val cleanBtn = findViewById<Button>(R.id.cleanBtn)
        cleanBtn.setOnClickListener {
            input?.setText("")
        }

        val plusMinusBtn = findViewById<Button>(R.id.plus_minusBtn)
        plusMinusBtn.setOnClickListener {
            updateText("-")
        }

        val parenthesesBtn = findViewById<Button>(R.id.parenthesesBtn)
        parenthesesBtn.setOnClickListener {
            val str = input?.text
            val cursorPos: Int? = input?.selectionStart
            var openPar = 0
            var closePar = 0

            for (i in 0 until cursorPos!!) {
                if (str?.get(i) == '(') {
                    openPar++
                } else if (str?.get(i) == ')') {
                    closePar++
                }
            }

            if (openPar == closePar || str?.last() == '(') {
                updateText("(")
            } else if (openPar > closePar && str?.last() != '(') {
                updateText(")")
            }

            input?.setSelection(cursorPos + 1)
        }

        val removeBtn = findViewById<ImageButton>(R.id.removeBtn)
        removeBtn.setOnClickListener {
            val strLength = input?.text?.length
            val cursorPos: Int? = input?.selectionStart
            if (strLength != 0 && cursorPos != 0) {
                val newStr = input?.text?.substring(0, strLength!! - 1)
                input?.setText(newStr)
                input?.setSelection(cursorPos!! - 1)
            }
        }

        val equalBtn = findViewById<Button>(R.id.equalBtn)
        equalBtn.setOnClickListener {
            var str = input?.text?.toString()
            str = str?.replace("×", "*")
            str = str?.replace("÷", "/")
            try {
                val expression = ExpressionBuilder(str).build()
                val result = expression.evaluate()
                input?.setText(result.toString())
            } catch (e: Exception) {
                input?.setText("Error")
            }
            input?.setSelection(input?.text.toString().length)
        }
    }

    private fun updateText(newStr: String) {
        val oldStr = input?.text
        val cursorPos: Int? = input?.selectionStart
        val leftStr = oldStr?.substring(0, cursorPos!!)
        val rightStr = oldStr?.substring(cursorPos!!)
        input?.setText("$leftStr$newStr$rightStr")
        input?.setSelection(cursorPos!! + 1)
    }
}