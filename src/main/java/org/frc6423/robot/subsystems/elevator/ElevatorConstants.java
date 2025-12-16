// Copyright (c) !!YEAR!! FRC 6423 - Ward Melville Iron Patriots
// https://github.com/wmironpatriots
// 
// Open Source Software; you can modify and/or share it under the terms of
// MIT license file in the root directory of this project

package org.frc6423.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Revolutions;
import static edu.wpi.first.units.Units.RevolutionsPerSecond;
import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import org.frc6423.lib.driver.ServoSubsystem.TaringConfig;
import org.frc6423.robot.Robot;

public class ElevatorConstants {

  /** Motor Constants */
  public static final String CANBUS_ID = "CANchan";

  public static final int PARENT_MOTOR_ID = 14;
  public static final int CHILD_MOTOR_ID = 15;

  /** Mechanical Constants */
  public static final double GEAR_REDUCTION = 3 / 1;

  public static final Distance DRUM_RADIUS = Inches.of(1.757 / 2);

  /** Position Constants */
  public static final Angle EPSILON = heightToAngle(Inches.of(1.5));

  public static final Angle POSITION_MAX = heightToAngle(Inches.of(24.0));

  public static final Angle POSITION_STOWED = heightToAngle(Inches.of(0.0));
  public static final Angle POSITION_INTAKING = heightToAngle(Inches.of(9.963));

  public static final Angle POSITION_L4 = heightToAngle(Inches.of(24.0));
  public static final Angle POSITION_L3 = heightToAngle(Inches.of(12.41));
  public static final Angle POSITION_L2 = heightToAngle(Inches.of(4.549));

  public static final Angle POSITION_MIN = heightToAngle(Inches.of(0.0));

  /** Motion Magic Constants */
  public static final AngularVelocity MAX_VELOCITY = heightToAngle(Inches.of(4.5)).per(Seconds);

  public static final AngularAcceleration MAX_ACCELERATION =
      heightToAngle(Inches.of(10.0)).per(Seconds).per(Seconds);

  /** Configs */

  /**
   * @return {@link TaringConfig} for the {@link Elevator} Subsystem
   */
  public static TaringConfig getTaringConfig() {
    var config = new TaringConfig();
    config.taredPosition = POSITION_MIN;
    config.taringTimeoutSeconds = Seconds.of(0.5);
    config.taringVoltage = Volts.of(-0.5);
    config.taredVelocity = RevolutionsPerSecond.of(0.01);

    return config;
  }

  /**
   * @return {@link TalonFXConfiguration} for the {@link Elevator} Subsystem
   */
  public static TalonFXConfiguration getTalonFxConfig() {
    var config = new TalonFXConfiguration();

    config.Audio.BeepOnBoot = true;
    config.Audio.BeepOnConfig = true;

    config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    config.CurrentLimits.SupplyCurrentLimitEnable = Robot.isReal();
    config.CurrentLimits.SupplyCurrentLimit = 80.0;
    config.CurrentLimits.SupplyCurrentLowerLimit = 80.0;
    config.CurrentLimits.SupplyCurrentLowerTime = 0.1;

    config.CurrentLimits.StatorCurrentLimitEnable = true;
    config.CurrentLimits.StatorCurrentLimit = 120.0;

    config.Voltage.PeakForwardVoltage = 12.0;
    config.Voltage.PeakReverseVoltage = -12.0;

    config.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
    config.SoftwareLimitSwitch.ForwardSoftLimitThreshold = POSITION_MAX.in(Revolutions);

    config.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
    config.SoftwareLimitSwitch.ReverseSoftLimitThreshold = POSITION_MIN.in(Revolutions);

    config.Feedback.SensorToMechanismRatio = GEAR_REDUCTION;
    config.ClosedLoopRamps.TorqueClosedLoopRampPeriod = 0.1;

    config.Slot0.GravityType = GravityTypeValue.Elevator_Static;
    config.Slot0.kG = 0.0;
    config.Slot0.kS = 0.0;
    config.Slot0.kV = 0.0;
    config.Slot0.kA = 0.0;
    config.Slot0.kP = 0.0;
    config.Slot0.kI = 0.0;
    config.Slot0.kD = 0.0;

    config.MotionMagic.MotionMagicCruiseVelocity = MAX_VELOCITY.in(RevolutionsPerSecond);
    config.MotionMagic.MotionMagicAcceleration =
        MAX_ACCELERATION.in(RevolutionsPerSecond.per(Seconds));

    return config;
  }

  public static Angle heightToAngle(Distance height) {
    return Revolutions.of(height.in(Meters) * 2 * Math.PI * DRUM_RADIUS.in(Meters));
  }

  public static Distance angleToHeight(Angle angle) {
    return Meters.of(angle.in(Revolutions) / (2 * Math.PI * DRUM_RADIUS.in(Meters)));
  }
}
