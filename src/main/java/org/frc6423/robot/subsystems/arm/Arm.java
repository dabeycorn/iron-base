// Copyright (c) !!YEAR!! FRC 6423 - Ward Melville Iron Patriots
// https://github.com/wmironpatriots
// 
// Open Source Software; you can modify and/or share it under the terms of
// MIT license file in the root directory of this project

package org.frc6423.robot.subsystems.arm;

import static edu.wpi.first.units.Units.Revolutions;

import edu.wpi.first.math.geometry.Rotation2d;
import org.frc6423.lib.driver.ServoSubsystem;
import org.frc6423.lib.hardware.ServoIO.Setpoint;
import org.frc6423.lib.hardware.ServoIOKrakenFoc;
import org.frc6423.robot.subsystems.elevator.ElevatorConstants;

/** Arm Subsystem */
public class Arm extends ServoSubsystem {

  /** Setpoints */
  public static final Setpoint SETPOINT_STOWED =
      Setpoint.withProfiledPosition(ArmConstants.POSITION_STOWED);

  public static final Setpoint SETPOINT_AVOIDING =
      Setpoint.withProfiledPosition(ArmConstants.POSITION_AVOIDING);
  public static final Setpoint SETPOINT_INTAKING =
      Setpoint.withProfiledPosition(ArmConstants.POSITION_INTAKING);

  public static final Setpoint SETPOINT_L2 =
      Setpoint.withProfiledPosition(ArmConstants.POSITION_L2);
  public static final Setpoint SETPOINT_L3 =
      Setpoint.withProfiledPosition(ArmConstants.POSITION_L3);
  public static final Setpoint SETPOINT_L4 =
      Setpoint.withProfiledPosition(ArmConstants.POSITION_L4);

  /** Constructor */
  public Arm() {
    super(
        true,
        ElevatorConstants.getTaringConfig(),
        ArmConstants.EPSILON,
        new boolean[0],
        new ServoIOKrakenFoc(
            ArmConstants.CANBUS_ID, ArmConstants.MOTOR_ID, ArmConstants.getTalonFxConfig()));
  }

  /** Getters */

  /**
   * @return {@link Rotation2d} representing the angular position of the {@link Arm} Subsystem
   */
  public Rotation2d getRotation2d() {
    return Rotation2d.fromRotations(getAngle().in(Revolutions));
  }
}
