package com.github.jangalinski.kobweb.tabler._foundation.widget

import androidx.compose.runtime.Composable
import com.github.jangalinski.kobweb.tabler._foundation.compose.KDiv
import com.github.jangalinski.kobweb.tabler._foundation.compose.KHtmlDiv
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.CancellationToken
import org.intellij.markdown.parser.MarkdownParser

@Composable
fun MarkdownText(markdown: String) {
  KHtmlDiv(html = markdownToHtml(markdown))
}

private fun markdownToHtml(markdown: String): String {
  val flavour = CommonMarkFlavourDescriptor()
  val parser = MarkdownParser(
    flavour = flavour,
    cancellationToken = CancellationToken.NonCancellable
  )

  val tree = parser.parse(
    root = MarkdownElementTypes.MARKDOWN_FILE,
    text = markdown as CharSequence
  )

  return HtmlGenerator(
    markdown,
    tree,
    flavour
  ).generateHtml()
}

