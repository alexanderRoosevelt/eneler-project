package com.project.eneler.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_video_views")
public class UserVideoViewEntity {

  @EmbeddedId
  private UserVideoViewId id;

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne
  @MapsId("videoId")
  @JoinColumn(name = "video_id")
  private VideoEntity video;

  @Column(name = "watched_until_end")
  private boolean watchedUntilEnd;

}
