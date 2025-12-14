// Copyright (c) !!YEAR!! FRC 6423 - Ward Melville Iron Patriots
// https://github.com/wmironpatriots
// 
// Open Source Software; you can modify and/or share it under the terms of
// MIT license file in the root directory of this project

package org.frc6423.robot.subsystem.elevator;

import edu.wpi.first.units.measure.Distance;
import org.frc6423.lib.driver.ServoSubsystem;
import org.frc6423.lib.hardware.ServoIO.Setpoint;
import org.frc6423.lib.hardware.ServoIOKrakenFoc;
import org.frc6423.lib.hardware.ServoIOKrakenSim;
import org.frc6423.robot.Robot;

public class Elevator extends ServoSubsystem {
  public static final Setpoint STOWED =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_MIN);
  public static final Setpoint SETPOINT_INTAKING =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_INTAKING);

  public static final Setpoint SETPOINT_L2 =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_L2);
  public static final Setpoint SETPOINT_L3 =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_L3);
  public static final Setpoint SETPOINT_L4 =
      Setpoint.withProfiledPosition(ElevatorConstants.POSITION_L4);

  protected Elevator() {
    super(
        Robot.isReal()
            ? new ServoIOKrakenFoc(
                ElevatorConstants.CANBUS,
                ElevatorConstants.PARENT_MOTOR_ID,
                ElevatorConstants.getConfig())
            : new ServoIOKrakenSim(),
        true,
        ElevatorConstants.getTaringConfig(),
        ElevatorConstants.EPSILON);
  }

  public Distance getFirstStageHeight() {
    return ElevatorConstants.angleToHeight(getPosition());
  }

  public Distance getCarraigeHeight() {
    return getFirstStageHeight().times(2);
  }
}
