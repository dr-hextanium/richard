package org.firstinspires.ftc.teamcode.command.sequences.specimen

import com.arcrobotics.ftclib.command.InstantCommand
import com.arcrobotics.ftclib.command.ParallelCommandGroup
import com.arcrobotics.ftclib.command.SequentialCommandGroup
import com.arcrobotics.ftclib.command.WaitCommand
import org.firstinspires.ftc.teamcode.command.deposit.SwingDeposit
import org.firstinspires.ftc.teamcode.command.deposit.PivotDeposit
import org.firstinspires.ftc.teamcode.command.extension.ExtensionToUntil
import org.firstinspires.ftc.teamcode.command.intake.CloseIntake
import org.firstinspires.ftc.teamcode.command.intake.SwingIntake
import org.firstinspires.ftc.teamcode.command.intake.PitchIntake
import org.firstinspires.ftc.teamcode.command.intake.TurnTurret
import org.firstinspires.ftc.teamcode.command.intake.TwistIntake
import org.firstinspires.ftc.teamcode.command.lift.LiftToUntil
import org.firstinspires.ftc.teamcode.hardware.Positions
import org.firstinspires.ftc.teamcode.hardware.Positions.Deposit
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Arm.INTERMEDIATE_ANGLE
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Claw.ASIDE_PITCH
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Claw.ASIDE_TWIST
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Claw.INTERMEDIATE_PITCH
import org.firstinspires.ftc.teamcode.hardware.Positions.Intake.Turret.ASIDE
import org.firstinspires.ftc.teamcode.utility.functions.deg

class PrepGrabbingSpecimen(intake: Boolean = true) : SequentialCommandGroup(
	PivotDeposit(Deposit.Pivot.GRAB_SPECIMEN),
	SwingDeposit(Deposit.Arm.GRAB_SPECIMEN),
	LiftToUntil(Positions.Lift.GRAB_SPECIMEN, time = 250),

	if (intake) {
		SequentialCommandGroup(
			CloseIntake(),

			ParallelCommandGroup(
				SequentialCommandGroup(
					ExtensionToUntil(0.0, time = 250),
					WaitCommand(200),
				),

				SequentialCommandGroup(
					PitchIntake(ASIDE_PITCH),
					SwingIntake(Positions.Intake.Arm.ASIDE),
					TwistIntake(ASIDE_TWIST),
					WaitCommand(200),
					TurnTurret(ASIDE)
				)
			),
		)
	} else {
		InstantCommand()
	}
)

