package sup
import cats.kernel.CommutativeMonoid
import cats.Eq

/**
  * The component's health status. It can only be Healthy or Sick - there's no middle ground (no Unknown state).
  * */
sealed trait Health extends Product with Serializable {

  def isHealthy: Boolean = this match {
    case Health.Healthy => true
    case Health.Sick    => false
  }
}

object Health {
  case object Healthy extends Health
  case object Sick    extends Health

  val healthy: Health = Healthy
  val sick: Health    = Sick

  implicit val eq: Eq[Health] = Eq.fromUniversalEquals

  /**
    * A monoid that'll return [[Sick]] if any of the combined values are sick, [[Healthy]] otherwise.
    * */
  implicit val allHealthyCommutativeMonoid: CommutativeMonoid[Health] = new CommutativeMonoid[Health] {
    override val empty: Health                         = healthy
    override def combine(x: Health, y: Health): Health = if (x.isHealthy) y else sick
  }
}