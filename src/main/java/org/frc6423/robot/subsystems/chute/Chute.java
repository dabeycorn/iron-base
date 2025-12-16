// Copyright (c) !!YEAR!! FRC 6423 - Ward Melville Iron Patriots
// https://github.com/wmironpatriots
// 
// Open Source Software; you can modify and/or share it under the terms of
// MIT license file in the root directory of this project

package org.frc6423.robot.subsystems.chute;

import org.frc6423.lib.driver.MotorSubsystem;
import org.frc6423.lib.hardware.ServoIOKrakenFoc;

public class Chute extends MotorSubsystem {
  public Chute() {
    super(
        new boolean[0],
        new ServoIOKrakenFoc(
            ChuteConstants.CANBUS_ID, ChuteConstants.MOTOR_ID, ChuteConstants.getTalonFxConfig()));
  }
}
