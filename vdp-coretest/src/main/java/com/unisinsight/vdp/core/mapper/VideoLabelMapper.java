package com.unisinsight.vdp.core.mapper;
import com.unisinsight.framework.common.base.Mapper;
import com.unisinsight.vdp.core.model.VideoLabel;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.Date;
import java.util.List;

/**
* 视频录像回放进度条标记
* @author unisinsight  [KF.hujunA@h3c.com]
* @date   2018/11/5 19:20
* @since  1.0
*/
public interface VideoLabelMapper extends Mapper<VideoLabel> {
  /**
   * 重写插入方法，返回新增的主键
   * @param record CollectionDevice
   * @return 收藏对象
   */
  @Override
  @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
  @Options(useGeneratedKeys = true, keyColumn = "label_id", keyProperty = "labelId")
  int insertSelective(VideoLabel record);

  /**
   * 根据设备Id和时间查询视频标记
   * @param id 巡航路径名称
   * @param dateStr 时间字符串HH-MM-dd
   * @return 巡航路径信息集合
   */
  List<VideoLabel> queryList(@Param("id") String id, @Param("dateStr") String dateStr);

  /**
   * 根据设备Id和时间查询视频标记
   * @param id 巡航路径名称
   * @param type 类型
   * @param startTime 开始时间
   * @param endTime 开始时间
   * @return 巡航路径信息集合
   */
  List<VideoLabel> validRepeat(@Param("id") String id, @Param("type") int type, @Param("startTime") String startTime, @Param("endTime") String endTime);
}