/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.felt.compile.artifact


/**
 * A cacheable Felt artifact
 */
abstract
class
FeltArtifact[INPUT]() extends AnyRef with FeltArtifactLocker with Equals {

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // private state
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  protected[this]
  var _isDeleted: Boolean = false

  private[this]
  var _lastTouch: Long = System.currentTimeMillis // epoch time of last access

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // subtype API
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * the type name of this artifact
   *
   * @return
   */
  def name: String

  /**
   * the associated input artifact
   *
   * @return
   */
  def input: INPUT

  /**
   * user friendly reference for this artifact
   *
   * @return
   */
  def tag: FeltArtifactTag

  /**
   * the cache key
   *
   * @return
   */
  def key: FeltArtifactKey

  /**
   * the generated ''source code'' for this artifact
   *
   * @return
   */
  def generatedSource: String

  def delete(key: FeltArtifactKey, tag: FeltArtifactTag): Unit = {
    _isDeleted = true
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // touch
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  final def lastTouch: Long = _lastTouch

  final def touch: this.type = {
    _lastTouch = System.currentTimeMillis
    this
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // synchronization
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  final
  def isDeleted: Boolean = _isDeleted

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // equality
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  final override
  def hashCode(): Int = key.hashCode()

  final override
  def equals(obj: scala.Any): Boolean = {
    obj match {
      case that: FeltArtifact[INPUT] =>
        this.key == that.key
    }
  }

  final override
  def canEqual(that: Any): Boolean = that.isInstanceOf[FeltArtifact[INPUT]]

}
