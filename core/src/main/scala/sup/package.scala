import cats.data.{NonEmptyList, OneAnd}
import sup.data.Tagged

package object sup {
  type ∘[F[_], G[_]] = { type λ[A] = F[G[A]] }

  //for 2.11 compatibility, this type alias must be next to the companion's alias
  type HealthReporter[F[_], G[_]] = HealthCheck[F, OneAnd[G, ?]]
  val HealthReporter: data.HealthReporter.type = data.HealthReporter

  type TaggedNel[Tag, A] = NonEmptyList[Tagged[Tag, A]]
}