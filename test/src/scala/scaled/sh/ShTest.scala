//
// Scaled Sh Mode - a Scaled major mode for editing shell scripts
// http://github.com/scaled/sh-mode/blob/master/LICENSE

package scaled.sh

import org.junit.Assert._
import org.junit._
import scaled.TextStore
import scaled.grammar._
import scaled.impl.BufferImpl

class ShTest {

  val testShCode = Seq(
    //                1         2         3
    //      0123456789012345678901234567890
    /* 0*/ "#!/bin/sh",
    /* 1*/ "",
    /* 2*/ "ARG=$0",
    /* 3*/ "FOO=\"bar\"",
    /* 4*/ "",
    /* 5*/ "for zim in $ZAMS ; do",
    /* 6*/ "  echo $zim",
    /* 7*/ "done").mkString("\n")

  val sh = Grammar.parseNDF(getClass.getClassLoader.getResource("sh.ndf"))
  val grammars = Seq(sh)

  @Test def debugGrammar () :Unit = {
    // xml.print(System.out)
    sh.scopeNames foreach println

    // val buffer = BufferImpl(new TextStore("Test.xml", "", testXMLCode))
    // val scoper = new Scoper(grammars, buffer, Nil)
    // println(scoper.showMatchers(Set("#internalSubset", "#tagStuff", "#entity")))
  }
}
