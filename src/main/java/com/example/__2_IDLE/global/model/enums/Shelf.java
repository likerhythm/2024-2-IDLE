package com.example.__2_IDLE.global.model.enums;

import com.example.__2_IDLE.global.model.Pose;
import lombok.Getter;

@Getter
public enum Shelf {
  SHELF_A(1L, new Pose(10, 15)), // TODO: 좌표 수정 필요
  SHELF_B(2L, new Pose(15, 20)),
  SHELF_C(3L, new Pose(10, 20));

  private final Long id;
  private Pose pose;

  Shelf(Long id, Pose pose) {
    this.id = id;
    this.pose = pose;
  }
}
