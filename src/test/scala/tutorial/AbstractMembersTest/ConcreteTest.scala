package tutorial.AbstractMembersTest


class ConcreteTest extends Abstract {
  override type T = String

  override def transform(x: String) = x + x

  override val initial = "hi"

  override var current = initial
}
