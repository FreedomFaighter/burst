/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.brio.press

import org.burstsys.brio.types.BrioTypes.BrioVersionKey
import org.burstsys.vitals.errors.VitalsException

/**
  * Press sources need to implement this API for each instance/object they
  * plan to press. This can be done either by direct mixin or via wrappers. this is
  * important in order to limit object creation during pressing
  */
trait BrioPressInstance extends Any {

  /**
    * @return the version of the schema supported by this instance
    */
  def schemaVersion: BrioVersionKey

  /**
   * @return a recognizable representation of where in the schema this instance is located
   */
  def context: String = ""

  /**
   * Throw an exception when the presser attempts to access a relation that this instance does not support
   * @param name the unsupported relation name
   */
  final def unknownRelation(name: String): Nothing = {
    val message = s"Error occurred during pressing: tried to press unexpected relation $context.$name"
    log warn message
    throw VitalsException(message)
  }

}

/**
  * A Press source is the client of the pressing framework. The Press source implements
  * this API and responds to its events.
  */
trait BrioPressSource extends Any {

  /**
    * Extract the root reference scalar relation and return instance (cannot be null).
    *
    * @return the root instance
    */
  def extractRootReferenceScalar(): BrioPressInstance

  /**
    * Extract a reference scalar relation and return instance or null.
    *
    * @param cursor         location in the traversal
    * @param parentInstance the instance that contain this relation
    * @return childInstance
    */
  def extractReferenceScalar(cursor: BrioPressCursor, parentInstance: BrioPressInstance): BrioPressInstance

  /**
    * Extract a reference vector relation and return iterator or null.
    * __NOTE__: all member instances __MUST__ be sorted by appropriate associated structure ordinal
    *
    * @param cursor         location in the traversal
    * @param parentInstance the instance that contain this relation
    * @return an iterable construct representing vector or null if this relation is null
    */
  def extractReferenceVector(cursor: BrioPressCursor, parentInstance: BrioPressInstance): Iterator[BrioPressInstance]

  /**
    * Extract a value scalar relationship to the capture object
    *
    * @param cursor         location in the traversal
    * @param parentInstance the instance that contain this relation
    * @param capture        place to put data to be pressed
    */
  def extractValueScalar(cursor: BrioPressCursor, parentInstance: BrioPressInstance, capture: BrioValueScalarPressCapture): Unit

  /**
    * Extract a value scalar relationship to the capture object. __NOTE__: all values __MUST__ be naturally sorted
    *
    * @param cursor         location in the traversal
    * @param parentInstance the instance that contain this relation
    * @param capture        place to put data to be pressed
    */
  def extractValueVector(cursor: BrioPressCursor, parentInstance: BrioPressInstance, capture: BrioValueVectorPressCapture): Unit

  /**
    * Extract a value map relation to the capture object. __NOTE__: all keys/values __MUST__ be naturally sorted
    *
    * @param cursor         location in the traversal
    * @param parentInstance the instance that contain this relation
    * @param capture        place to put data to be pressed
    */
  def extractValueMap(cursor: BrioPressCursor, parentInstance: BrioPressInstance, capture: BrioValueMapPressCapture): Unit

}


