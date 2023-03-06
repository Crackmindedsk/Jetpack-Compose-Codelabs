package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.text.NumberFormat

class TipTime:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                TipTimeScreen()
            }
        }
    }
}

@Preview
@Composable
fun TipTimeScreen(){
    TipTimeCall()
}
@Composable
fun TipTimeCall(){
    var amountInput by remember {
        mutableStateOf("")
    }
    var tipInut by remember {
        mutableStateOf("")
    }
    var roundUp by remember {
        mutableStateOf(false)
    }
    val amount=amountInput.toDoubleOrNull()?:0.0
    val tipamount=tipInut.toDoubleOrNull()?:0.0
    val focusManager= LocalFocusManager.current
    val tip= calculateTip(amount, tipamount,roundUp)
    Column(modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = stringResource(id = R.string.tip), fontSize = 24.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(16.dp))
        EditNumberField(R.string.bill,
            KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),value=amountInput, onValueChange={amountInput=it}, keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(
                FocusDirection.Down)}))
        Spacer(Modifier.height(16.dp))
        EditNumberField(label = R.string.tip_percentage, KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done), value = tipInut, onValueChange = {tipInut=it}, keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}))
        Spacer(modifier = Modifier.height(16.dp))
        RoundTheTipRow(roundUp = roundUp, onRoundUpChanged ={roundUp=it} )
        Spacer(modifier = Modifier.height(24.dp))
        Text( text= stringResource(id = R.string.amount,tip), modifier = Modifier.align(Alignment.CenterHorizontally),
        fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EditNumberField(@StringRes label:Int, keyboardOptions:KeyboardOptions, value:String,onValueChange:(String)->Unit, keyboardActions: KeyboardActions){
    TextField(value = value,
        onValueChange = onValueChange,
        label = {Text(stringResource(label))},
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions=keyboardActions
    )
}
@Composable
fun RoundTheTipRow(roundUp:Boolean, onRoundUpChanged:(Boolean)->Unit, modifier: Modifier=Modifier){
    Row(modifier = Modifier
        .fillMaxWidth()
        .size(48.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(stringResource(id = R.string.round), fontSize = 24.sp)
        Switch(checked = roundUp, onCheckedChange = onRoundUpChanged, modifier= modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End),
        colors = SwitchDefaults.colors(uncheckedThumbColor = Color.DarkGray))
    }
}

@VisibleForTesting
internal fun calculateTip(
    amount:Double,
    tipPercent:Double=15.0,
    roundUp: Boolean
):String{
    var tip=tipPercent/100 * amount
    if(roundUp)
        tip=kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
}