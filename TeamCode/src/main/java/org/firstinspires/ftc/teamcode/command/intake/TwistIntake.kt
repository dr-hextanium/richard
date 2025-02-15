package org.firstinspires.ftc.teamcode.command.intake

import org.firstinspires.ftc.teamcode.command.CommandTemplate
import org.firstinspires.ftc.teamcode.hardware.Robot

class TwistIntake(val angle: Double) : CommandTemplate() {
	override fun initialize() { Robot.Subsystems.intake.twist(angle) }

	override fun execute() {}

	override fun isFinished(): Boolean = true
}

class TwistIntakeRelatively(val angle: Double) : CommandTemplate() {
	override fun initialize() {
		val angle = Robot.Subsystems.intake.diffy.state.horizontal + angle
		Robot.Subsystems.intake.twist(angle)
	}

	override fun execute() {}

	override fun isFinished(): Boolean = true
}