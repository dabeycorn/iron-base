// Copyright (c) !!YEAR!! FRC 6423 - Ward Melville Iron Patriots
// https://github.com/wmironpatriots
// 
// Open Source Software; you can modify and/or share it under the terms of
// MIT license file in the root directory of this project

package org.frc6423.robot.subsystems.arm;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecondPerSecond;
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

public class ArmConstants {

  /** Motor Constants */
  public static final String CANBUS_ID = "CANchan";

  public static final int MOTOR_ID = 13;

  /** Mechanical Constants */
  public static final double GEAR_REDUCTION = 5 * 5 * 2;

  public static final Distance LENGTH = Inches.of(7.2);

  /** Position Constants */
  public static final Angle EPSILON = Degrees.of(1.5);

  public static final Angle POSITION_MAX = Degrees.of(90);

  public static final Angle POSITION_STOWED = Degrees.of(90);
  public static final Angle POSITION_AVOIDING = Degrees.of(65);
  public static final Angle POSITION_INTAKING = Degrees.of(-90);

  public static final Angle POSITION_L2 = Degrees.of(21.98);
  public static final Angle POSITION_L3 = Degrees.of(21.98);
  public static final Angle POSITION_L4 = Degrees.of(74.5);

  public static final Angle POSITION_MIN = Degrees.of(-90);

  /** Motion Magic Constants */
  public static final AngularVelocity MAX_VELOCITY = RadiansPerSecond.of(5.5);

  public static final AngularAcceleration MAX_ACCELERATION = RadiansPerSecondPerSecond.of(17);

  /** Configs */

  /**
   * @return {@link TaringConfig} for the {@link Arm} Subsystem
   */
  public static TaringConfig getTaringConfig() {
    var config = new TaringConfig();
    config.taredPosition = POSITION_MAX;
    config.taringTimeoutSeconds = Seconds.of(0.5);
    config.taringVoltage = Volts.of(0.5);
    config.taredVelocity = RevolutionsPerSecond.of(0.01);

    return config;
  }

  public static TalonFXConfiguration getTalonFxConfig() {
    var config = new TalonFXConfiguration();

    config.Audio.BeepOnBoot = true;
    config.Audio.BeepOnConfig = true;

    config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

    config.CurrentLimits.SupplyCurrentLimitEnable = Robot.isReal();
    config.CurrentLimits.SupplyCurrentLimit = 40.0;
    config.CurrentLimits.SupplyCurrentLowerLimit = 40.0;
    config.CurrentLimits.SupplyCurrentLowerTime = 0.1;

    config.CurrentLimits.StatorCurrentLimitEnable = true;
    config.CurrentLimits.StatorCurrentLimit = 80.0;

    config.Voltage.PeakForwardVoltage = 12.0;
    config.Voltage.PeakReverseVoltage = -12.0;

    config.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
    config.SoftwareLimitSwitch.ForwardSoftLimitThreshold = POSITION_MAX.in(Revolutions);

    config.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
    config.SoftwareLimitSwitch.ReverseSoftLimitThreshold = POSITION_MIN.in(Revolutions);

    config.Feedback.SensorToMechanismRatio = GEAR_REDUCTION;
    config.ClosedLoopRamps.TorqueClosedLoopRampPeriod = 0.1;

    config.Slot0.GravityType = GravityTypeValue.Arm_Cosine;
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
}
