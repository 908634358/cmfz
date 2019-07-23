package com.baizhi.repository.jpa;

import com.baizhi.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerJapRepository extends JpaRepository<Banner, String> {


}
