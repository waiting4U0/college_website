package cn.edu.swpu.info.college_website.dao.base;

import cn.edu.swpu.info.college_website.domain.exception.AppException;

import java.io.Serializable;
import java.util.List;


public abstract class BaseDaoImpl<T, KEY extends Serializable> extends MyBatisSupport implements BaseDao<T, KEY> {
    private static final String DEFAULT_INSERT_KEY = "insertEntry";
    private static final String DEFAULT_INSERT_LAST_SEQUENCE_KEY = "lastSequence";
    private static final String DEFAULT_DELETE_ARRAY_KEY = "deleteByArrayKey";
    private static final String DEFAULT_DELETE_CONDTION = "deleteByCondtion";
    private static final String DEFAULT_UPDATE_KEY = "updateByKey";
    private static final String DEFAULT_SELECT_ARRAY_KEY = "selectEntryArray";
    private static final String DEFAULT_SELECT_CONDTION = "selectEntryList";
    private static final String DEFAULT_SELECT_CONDTION_COUNT = "selectEntryListCount";
    private static final String DEFAULT_SELECT_KEY = "selectOneByKey";

    /**
     * 获取命名空前前缀
     *
     * @param statement
     * @return
     */
    public abstract String getNameSpace(String statement);

    public int insertEntry(T... t) {
        int result = 0;
        if (t == null || t.length <= 0) {
            return result;
        }
        for (T o : t) {
            if (o != null) {
                result += this.insert(getNameSpace(DEFAULT_INSERT_KEY), o);
            }
        }
        return result;
    }

    public Long insertEntryCreateId(T t) {
        @SuppressWarnings("unchecked")
        Integer result = this.insertEntry(t);
        if (result > 0) {
            Long id = (Long) select(getNameSpace(DEFAULT_INSERT_LAST_SEQUENCE_KEY), null);
            if (id != null && id > 0) {
                try {
                    Class<?> clz = t.getClass();
                    clz.getMethod("setId", Long.class).invoke(t, id);//最后一次插入编号
                    return id;
                } catch (Exception e) {
                    throw new AppException("设置新增主键失败", e);
                }
            }
        }
        return result.longValue();
    }

    public int deleteByKey(KEY... key) {
        return this.delete(getNameSpace(DEFAULT_DELETE_ARRAY_KEY), key);
    }

    public int deleteByKey(T t) {
        return this.delete(getNameSpace(DEFAULT_DELETE_CONDTION), t);
    }

    public int updateByKey(T t) {
        return this.update(getNameSpace(DEFAULT_UPDATE_KEY), t);
    }

    public T selectOneByKey(T t) {
        return (T) this.select(getNameSpace(DEFAULT_SELECT_KEY),t);
    }

    public T selectEntry(KEY key) {
        @SuppressWarnings("unchecked")
        List<T> list = this.selectEntryList(key);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public List<T> selectEntryList(KEY... key) {
        if (key == null || key.length <= 0) {
            return null;
        }
        return this.selectList(getNameSpace(DEFAULT_SELECT_ARRAY_KEY), key);
    }

    public List<T> selectEntryList(T t) {
        return this.selectList(getNameSpace(DEFAULT_SELECT_CONDTION), t);
    }

    public Integer selectEntryListCount(T t) {
        return this.select(getNameSpace(DEFAULT_SELECT_CONDTION_COUNT), t);
    }
}
