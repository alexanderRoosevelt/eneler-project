package com.project.eneler.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "videos")
@AllArgsConstructor
@NoArgsConstructor
public class VideoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String url;
  @Column(name = "image_url")
  private String imageUrl;
  @Column(name = "created_date")
  private LocalDateTime createdDate;
  private LocalDate access;
  @Column(name = "short_info")
  private String shortInfo;
  @Column(name = "full_info")
  private String fullInfo;
}
