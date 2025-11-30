package org.utl.a03_calculadora_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.utl.a03_calculadora_jetpack.ui.theme._03_Calculadora_JetPackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                Calculadora()
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun Preview() {
    Calculadora()

}
@Composable
fun Calculadora(){
    //declaramos variables de estado para almacenar los valores actuales de la calculadora
    var currentValue by remember { mutableStateOf("0") }
    var previousValue by remember { mutableStateOf("0") }
    var operationVale by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFF000000))
            .padding(16.dp)
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //diseñamos la pantalla de la calculadora
            DisplaySection(
            currentValue=currentValue,
            previousValue=previousValue,
            operation=operationVale,
            modifier = Modifier.fillMaxWidth().weight(0.3f)
                )
            KeyboardSection(
                onNumberClick={number->
                    currentValue= if (currentValue=="0")number.toString()
                                    else currentValue+number.toString()
                },
                onOperationClick={op->
                        when(op){
                            "C"->{
                                currentValue="0"
                                previousValue=""
                                operationVale=""
                            }
                            "="->{
                                if(previousValue.isNotEmpty() && operationVale.isNotEmpty()){
                                    val result=calcularResult(
                                        previousValue.toDouble(),
                                        currentValue.toDouble(),
                                        operationVale
                                    )
                                    currentValue=result.toString()
                                    previousValue+""
                                    operationVale=""
                                }
                            }
                            else -> {
                                if (currentValue.isNotEmpty()){
                                    previousValue=currentValue
                                    currentValue="0"
                                    operationVale=op
                                }
                            }
                        }
                    }

                )


        }
    }
}
@Composable
fun KeyboardSection(
    onNumberClick: (Int) -> Unit,
    onOperationClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column (modifier=modifier,
        verticalArrangement =
            Arrangement.spacedBy(8.dp))
    {
        //Decaramos la primer fila
        Row(
            modifier= Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            //B oton para el clear
            CalculatorButton(
                text="C",
                color= Color(0xFFFF9500),
                textColor= Color.White,
                modifier= Modifier.weight(1f)
            ){
                onOperationClick("C")
            }
            //aregamos 3 espacion vacios
            Spacer(modifier= Modifier.weight(1f))
            Spacer(modifier= Modifier.weight(1f))
            Spacer(modifier= Modifier.weight(1f))
        }
        val buttonList=listOf(
            listOf("7","8","9","/"),
            listOf("4","5","6","x"),
            listOf("1","2","3","-"),
            listOf("0",".","=","+"),
        )
        buttonList.forEach {
            row->
                Row(modifier= Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                )
                {
                    row.forEach {
                        buttonText->
                            CalculatorButton(text = buttonText,
                                color = Color(0xFF0066FF),
                                textColor = Color.White,
                                modifier= Modifier.weight(1f)) {
                                when(buttonText){
                                    in listOf("0","1","2","3","4","5","6","7","8","9")->
                                        onNumberClick(buttonText.toInt())
                                    else ->
                                        onOperationClick(buttonText)
                                }
                            }
                    }
                }
        }
    }
}
@Composable
fun CalculatorButton(
    text:String,
    color: Color,
    textColor:Color,
    modifier:Modifier=Modifier,
    onClick:()->Unit
){
    val buttonModifier=modifier
        .aspectRatio(1f)//para que
        .background(color, RoundedCornerShape(12.dp))//fomndo radial
        .padding(8.dp)
    Box(modifier=buttonModifier.clickable(){onClick()},
        contentAlignment = Alignment.Center)
    {
        Text(text=text,
            color=textColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium)
    }
}

@Composable
fun DisplaySection(
    currentValue: String,
    previousValue: String,
    operation: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF564E4E), RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        // Operación anterior
        if (previousValue.isNotEmpty()) {
            Text(
                text = "$previousValue $operation",
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
        // Valor actual
        Text(
            text = currentValue,
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}
private fun calcularResult(num1: Double,num2: Double,operation: String):Double{
    return when(operation) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "x" -> num1 * num2
        "/" -> if (num2 != 0.0) num1 / num2 else 0.0
        else -> num2
    }
}