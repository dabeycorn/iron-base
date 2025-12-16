// Copyright (c) !!YEAR!! FRC 6423 - Ward Melville Iron Patriots
// https://github.com/wmironpatriots
// 
// Open Source Software; you can modify and/or share it under the terms of
// MIT license file in the root directory of this project

package org.frc6423.robot.subsystems.elevator;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.units.measure.Distance;
import org.frc6423.lib.driver.ServoSubsystem;
import org.frc6423.lib.hardware.ServoIO.Setpoint;
import org.frc6423.lib.hardware.ServoIOKrakenFoc;

@Logged
public class Elevator extends ServoSubsystem {

  /** Setpoints */
  public static final Setpoint SETPOINT_STOWED =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_STOWED);

  public static final Setpoint SETPOINT_INTAKING =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_INTAKING);

  public static final Setpoint SETPOINT_L2 =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_L2);
  public static final Setpoint SETPOINT_L3 =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_L3);
  public static final Setpoint SETPOINT_L4 =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_L4);

  /** Constructor */
  public Elevator() {
    super(
        true,
        ElevatorConstants.getTaringConfig(),
        ElevatorConstants.EPSILON,
        new boolean[] {true},
        new ServoIOKrakenFoc(
            ElevatorConstants.CANBUS_ID,
            ElevatorConstants.PARENT_MOTOR_ID,
            ElevatorConstants.getTalonFxConfig()),
        new ServoIOKrakenFoc(
            ElevatorConstants.CANBUS_ID,
            ElevatorConstants.CHILD_MOTOR_ID,
            ElevatorConstants.getTalonFxConfig()));
  }

  /** Getters */
  public Distance getFirstStageHeight() {
    return ElevatorConstants.angleToHeight(getAngle());
  }

  public Distance getCarriageHeight() {
    return getFirstStageHeight().times(2);
  }
}
