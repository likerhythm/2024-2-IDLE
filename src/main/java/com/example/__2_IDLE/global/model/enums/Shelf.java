package com.example.__2_IDLE.global.model.enums;

import com.example.__2_IDLE.robot_manager.pos.Pos;
import lombok.Getter;

@Getter
public enum Shelf {
  SHELF_A(1L, new Pos(10, 15)), // TODO: 좌표 수정 필요
  SHELF_B(2L, new Pos(15, 20)),
  SHELF_C(3L, new Pos(10, 20));

  private final Long id;
  private Pos pos;

  Shelf(Long id, Pos pos) {
    this.id = id;
    this.pos = pos;
  }
}