//
// Scaled Sh Mode - a Scaled major mode for editing shell scripts
// http://github.com/scaled/sh-mode/blob/master/LICENSE

package scaled.sh

import scaled._
import scaled.code.Indenter
import scaled.grammar._
import scaled.code.{CodeConfig, Commenter}

object ShConfig extends Config.Defs {
  import EditorConfig._
  import CodeConfig._
  import GrammarConfig._

  // maps TextMate grammar scopes to Scaled style definitions
  val effacers = List(
    effacer("comment.line", commentStyle),
    effacer("comment.block", docStyle),
    effacer("constant", constantStyle),
    effacer("invalid", warnStyle),
    effacer("keyword", keywordStyle),
    effacer("string", stringStyle),
    effacer("variable", stringStyle),
    effacer("storage", variableStyle)
  )

  // maps TextMate grammar scopes to Scaled syntax definitions
  val syntaxers = List(
    syntaxer("comment.line", Syntax.LineComment),
    syntaxer("comment.block", Syntax.DocComment),
    syntaxer("constant", Syntax.OtherLiteral),
    syntaxer("string", Syntax.StringLiteral)
  )

  val grammars = resource("sh.ndf")(Grammar.parseNDFs)
}

@Major(name="sh",
       tags=Array("code", "project", "sh"),
       pats=Array(".*\\.sh", ".*\\.bash", "\\.profile", "\\.bash(rc|_profile|_login|_logout)"),
       ints=Array("bash", "sh", "csh", "ksh", "tcsh"),
       desc="A major mode for editing shell scripts.")
class ShMode (env :Env) extends GrammarCodeMode(env) {

  override def dispose () {} // nada for now

  override def configDefs = ShConfig :: super.configDefs
  override def grammars = ShConfig.grammars.get
  override def effacers = ShConfig.effacers
  override def syntaxers = ShConfig.syntaxers

  // override def createIndenter() = new XmlIndenter(buffer, config)
  override val commenter = new Commenter() {
    override def linePrefix = "#"
    override def blockPrefix = "#"
    override def docOpen = "##"
  }
}
