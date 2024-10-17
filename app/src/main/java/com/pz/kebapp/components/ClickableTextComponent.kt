package com.pz.kebapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
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
        pushStringAnnotation(
            tag = "ANNOTATION",
            annotation = valueAnnotated
        )
        withStyle(style = SpanStyle(color = TextFieldPrimaryColor)) {
            append(valueAnnotated)
        }
        pop()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = valueInitial,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            )
        )
        Text(
            text = annotatedString,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = nunitoSansFontFamily,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .clickable {
                    annotatedString
                        .getStringAnnotations(
                            tag = "ANNOTATION",
                            start = 0,
                            end = annotatedString.length
                        )
                        .firstOrNull()
                        ?.let { annotation ->
                            onTextSelected(annotation.item)
                        }
                }
        )
    }
}