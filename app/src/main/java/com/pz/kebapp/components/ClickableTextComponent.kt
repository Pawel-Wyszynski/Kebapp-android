package com.pz.kebapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pz.kebapp.ui.theme.TextFieldPrimaryColor
import com.pz.kebapp.ui.theme.nunitoSansFontFamily

@Composable
fun ClickableTextComponent(
    valueInitial: String,
    valueAnnotated: String,
    onTextSelected: (String) -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append(valueInitial)
        withStyle(style = SpanStyle(color = TextFieldPrimaryColor)) {
            pushStringAnnotation(tag = valueAnnotated, annotation = valueAnnotated)
            append(valueAnnotated)
        }
    }
    ClickableText(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = nunitoSansFontFamily,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    if (span.item == valueAnnotated) {
                        onTextSelected(span.item)
                    }
                }
        })
}