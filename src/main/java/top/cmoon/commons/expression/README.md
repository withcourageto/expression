### 表达式说明

这个表达式引擎是用于解析本系统中 sql 的部分表达式，为了模拟 Mybatis 的部分语法，本系统使用了一套sql解析机制，但是其中的表达式无法动态解析，所以需要使用一个表达式引擎

 为什么要自定义实现语言表达式引擎，因为spring的表达式引擎不能符合需求，不能将类似 school[] 的键识别为标识符，并且标识符必须以#开头，导致无法与MyBaties语法保持一致
 
 
使用案列：

	<select id="CheckStatEntry">
		select ...
		from ..._stat_table tt
		
		<!-- 此处使用了表达式 schTypes[] != null -->
		<if test="schTypes[] != null">
			left join ..._info bsi on tt.school_id = bsi.tfk
		</if>
		
		where exam_id = #{examId} 
			<if test="stages[] != null">
				and stage_id in (#{stages[]})
			</if>
			<if test="njs[] != null">
				and concat(stage_id, grade_start_year) in (#{njs[]})
			</if> 
			<if test="schTypes[] != null">
				and (bsi.XXLX_FK in(#{schTypes[]}) or bsi.XXLX_FK is null)
			</if>
			<if test="zeroSubjCount != null">
				and  #{zeroSubjCount} >= tt.ZERO_SUBJ_COUNT 
			</if>
		GROUP BY school_id, subject_id
	</select>
	
### 扩展
现在引擎只实现了简单的布尔运算，如果需要实现更多的功能，继承 ```Token``` 类
