package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                )
                {
                    UnitConv()
                }
            }
        }
    }
}

@Composable
fun UnitConv(){

    Column(){
        Text(text="Mass Conversion", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold, fontSize=24.sp)
        var amountInput by remember{ mutableStateOf("0") }
        var amount=amountInput.toDoubleOrNull()?:0.0
        var roundUp by remember{mutableStateOf(false)}
        var convertedVal=Conversion(amount,roundUp)
        ReverseConv(roundUp=roundUp,onRoundUpChanged={ roundUp=it})
        if(roundUp)
        {
            Row(){
                Text(text = "Enter mass in grams", modifier = Modifier.padding(16.dp))
                InputField(amountInput, valueChanged = { amountInput = it })
            }
            Text(
                text = stringResource(R.string.Conversion_gr, convertedVal),
                modifier = Modifier.padding(16.dp)
            )

        }
        else{
            Row(){
                Text(text = "Enter mass in kgs", modifier = Modifier.padding(16.dp))
                InputField(amountInput, valueChanged = { amountInput = it })
            }
            Text(
                text = stringResource(R.string.Conversion_kg, convertedVal),
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(text="Length Conversion", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold, fontSize=24.sp)
        var valueIn by remember{ mutableStateOf("0") }
        var value=valueIn.toDoubleOrNull()?:0.0
        var opposite by remember{mutableStateOf(false)}
        var convVal=Conversion2(value,opposite)
        ReverseConv(roundUp=opposite,onRoundUpChanged={ opposite=it})
        if(opposite)
        {
            Row(){
                Text(text = "Enter length in inches", modifier = Modifier.padding(16.dp))
                InputField(valueIn, valueChanged = { valueIn = it })
            }
            Text(
                text = stringResource(R.string.Conversion_in, convVal),
                modifier = Modifier.padding(16.dp)
            )

        }
        else{
            Row(){
                Text(text = "Enter length in meters", modifier = Modifier.padding(16.dp))
                InputField(valueIn, valueChanged = { valueIn = it })
            }
            Text(
                text = stringResource(R.string.Conversion_m, convVal),
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(text="Currency Conversion", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold, fontSize=24.sp)
        var currIn by remember{ mutableStateOf("0") }
        var curr=currIn.toDoubleOrNull()?:0.0
        var conv by remember{mutableStateOf(false)}
        var convVal2=Conversion3(curr,conv)
        ReverseConv(roundUp=conv,onRoundUpChanged={ conv=it})
        if(conv)
        {
            Row(){
                Text(text = "Enter amount in US Dollars", modifier = Modifier.padding(16.dp))
                InputField(currIn, valueChanged = { currIn = it })
            }
            Text(
                text = stringResource(R.string.Conversion_inr, convVal2),
                modifier = Modifier.padding(16.dp)
            )

        }
        else{
            Row(){
                Text(text = "Enter amount in Indian Rupees", modifier = Modifier.padding(16.dp))
                InputField(currIn, valueChanged = { currIn = it })
            }
            Text(
                text = stringResource(R.string.Conversion_usd, convVal2),
                modifier = Modifier.padding(16.dp)
            )
        }

        }
    }

@Composable
fun Conversion(amount:Double, roundUp: Boolean):String{
    var ans=0.0
    if(roundUp){
        ans=amount/1000
    }
    else{
        ans = amount*1000
    }
    return NumberFormat.getNumberInstance().format(ans)
}

@Composable
fun Conversion2(value:Double, opposite:Boolean):String{
    var ans=0.0
    if(opposite)
        ans=value/39.37
    else
        ans=value*39.37
    return NumberFormat.getNumberInstance().format(ans)
}

@Composable
fun Conversion3(curr:Double, conv:Boolean):String{
    var ans=0.0
    if(conv)
        ans=curr*82.04
    else
        ans=curr/82.04
    return NumberFormat.getNumberInstance().format(ans)
}

@Composable
fun ReverseConv(roundUp:Boolean, onRoundUpChanged:(Boolean)->Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {

        Text(text="Reverse conversion",modifier = Modifier.padding(start=16.dp, bottom=5.dp))
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            colors = SwitchDefaults.colors(
                androidx.compose.ui.graphics.Color.Cyan,
                uncheckedThumbColor = androidx.compose.ui.graphics.Color.DarkGray
            )
        )
    }
}

@Composable
fun InputField(amountInput:String, valueChanged:(String)->Unit){
    TextField(value = amountInput, onValueChange = valueChanged)
}

@Composable
fun calc(amount:Double, multiplyfact:Double):String{
    var ans=amount*1000
    return NumberFormat.getNumberInstance().format(ans)
}

@Preview
@Composable
fun UnitConPreview(){
    UnitConverterTheme() {

    }
}