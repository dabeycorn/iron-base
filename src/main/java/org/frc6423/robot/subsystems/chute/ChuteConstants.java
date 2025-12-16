// Copyright (c) !!YEAR!! FRC 6423 - Ward Melville Iron Patriots
// https://github.com/wmironpatriots
// 
// Open Source Software; you can modify and/or share it under the terms of
// MIT license file in the root directory of this project

package org.frc6423.robot.subsystems.chute;

import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.Voltage;
import org.frc6423.robot.Robot;

public class ChuteConstants {

  /** Motor Constants */
  public static final String CANBUS_ID = "RIO";

  public static final int MOTOR_ID = 3;

  /** Speed Constants */
  public static final Voltage SPEED_INTAKING = Volts.of(-0.1);

  public static final Voltage SPEED_OUTAKING = Volts.of(0.1);

  /** Configs */
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
    config.CurrentLimits.StatorCurrentLimit = 40.0;

    config.Voltage.PeakForwardVoltage = 12.0;
    config.Voltage.PeakReverseVoltage = -12.0;

    return config;
  }
}
