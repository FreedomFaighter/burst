/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.hydra.test.cases.quo.valuemaps

import org.burstsys.fabric.wave.execution.model.result.group.FabricResultGroup
import org.burstsys.fabric.wave.execution.model.result.row.FabricResultRow
import org.burstsys.hydra.test.cases.quo.parameters.HydraQuoParameters01.{analysisName, frameName}
import org.burstsys.hydra.test.cases.support.HydraUseCase

object HydraQuoValueMap04 extends HydraUseCase(1, 1, "quo") {

  //  override val sweep = new B40BD3A6B97E1417987E749D725FCCFC4

  override val frameSource: String =
    s"""
       |frame $frameName {
       |  cube user {
       |    limit = 9999
       |    cube user.sessions.events {
       |      aggregates {
       |        count:sum[long]
       |      }
       |    }
       |  }
       |  user.sessions.events.parameters => {
       |    situ => {
       |      if( key(user.sessions.events.parameters) == "cg") {
       |        $analysisName.$frameName.count = 1
       |      }
       |    }
       |  }
       |}
       |""".stripMargin

  override def
  validate(implicit result: FabricResultGroup): Unit = {
    val r = result.resultSets(result.resultSetNames(frameName))
    assertLimits(r)

    found(r.rowSet) should equal(expected)
  }


  def found(rowSet: Array[FabricResultRow]): Array[_] = {
    rowSet.map {
      row => row.cells(0).asLong
    }
  }

  val expected: Array[Any] = Array(14555)


}
