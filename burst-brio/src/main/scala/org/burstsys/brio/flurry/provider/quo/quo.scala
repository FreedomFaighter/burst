/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.brio.flurry.provider

import org.burstsys.brio.press.BrioPressInstance
import org.burstsys.brio.provider.BrioSchemaProvider
import org.burstsys.brio.types.BrioTypes.BrioVersionKey
import org.burstsys.tesla.buffer.mutable.TeslaMutableBuffer
import org.burstsys.vitals.errors.VitalsException
import org.burstsys.vitals.errors.safely
import org.burstsys.vitals.logging._

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

package object quo extends VitalsLogger {

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////
  // SCHEMA
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * provider class for the brio 'test' schema
   */
  final case class QuoSchemaProvider() extends BrioSchemaProvider {

    val names: Array[String] = Array("quo", "Quo", "org.burstsys.schema.quo")

    val schemaResourcePath: String = "org/burstsys/brio/flurry/schema/quo"

  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////
  // MOCK DATA MODEL
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////

  sealed trait QuoMockInstance extends BrioPressInstance {
    final override val schemaVersion: BrioVersionKey = 3
  }

  final case
  class QuoMockUser(
                     flurryId: String = "",
                     project: QuoMockProject = QuoMockProject(),
                     sessions: Array[QuoMockSession] = Array(QuoMockSession()),
                     segments: Array[QuoMockSegment] = Array(QuoMockSegment()),
                     channels: Array[QuoMockChannel] = Array(QuoMockChannel()),
                     personas: Array[QuoMockPersona] = Array(QuoMockPersona()),
                     deviceModelId: Long = -1L,
                     deviceSubModelId: Long = -1L,
                     parameters: Map[String, String] = Map("k1" -> "k2")
                   ) extends QuoMockInstance

  final case
  class QuoMockProject(
                        projectId: Long = -1L,
                        installTime: Long = -1L,
                        lastUsedTime: Long = -1L,
                        retainedTime: Long = -1L,
                        cityId: Long = -1L,
                        stateId: Long = -1L,
                        countryId: Long = -1L,
                        regionId: Long = -1L,
                        localeId: Long = -1L,
                        languageId: Long = -1L,
                        birthDate: Long = -1L,
                        gender: Byte = -1
                      ) extends QuoMockInstance

  final case
  class QuoMockSession(
                        sessionId: Long = -1L,
                        osVersion: Long = -1L,
                        startTime: Long = -1L,
                        totalErrors: Long = -1L,
                        timeZoneId: Long = -1L,
                        localeId: Long = -1L,
                        regionId: Long = -1L,
                        countryId: Long = -1L,
                        stateId: Long = -1L,
                        cityId: Long = -1L,
                        carrierId: Long = -1L,
                        agentVersionId: Long = -1L,
                        appVersionId: Long = -1L,
                        duration: Long = -1L,
                        totalEvents: Long = -1L,
                        events: Array[QuoMockEvent] = Array(QuoMockEvent()),
                        providedOrigin: String = "",
                        mappedOrigin: Long = -1L,
                        originSourceType: Long = -1L,
                        originMethodType: Long = -1L,
                        parameters: Map[String, String] = Map("k1" -> "k2"),
                        birthDateReported: Long = -1L,
                        genderReported: Byte = -1
                      ) extends QuoMockInstance

  final case
  class QuoMockEvent(eventId: Long = -1L,
                     startTime: Long = -1L,
                     duration: Long = -1L,
                     order: Int = -1,
                     eventType: Byte = -1,
                     parameters: Map[String, String] = Map("k1" -> "k2")
                    ) extends QuoMockInstance

  final case
  class QuoMockSegment(segmentId: Long = -1L) extends QuoMockInstance

  final case
  class QuoMockChannel(
                        channelId: Long = -1L,
                        networkId: Long = -1L,
                        isQuality: Boolean = false
                      ) extends QuoMockInstance

  final case
  class QuoMockPersona(personaId: Long = -1L) extends QuoMockInstance

}
