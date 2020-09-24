package cn.lastwhisper.modular.mapper;

import cn.lastwhisper.modular.pojo.Debitnote;

import java.util.List;

public interface DebitnoteMapper {

    public List<Debitnote> selectDebitnotelistByPage(Debitnote debitnote);

    public List<Debitnote> selectDebitnoteByDebitname(Debitnote debitnote);

    public Integer insertDebitnote(Debitnote debitnote);

    Integer updateDebitnoteById(Debitnote debitnote);

    Integer deleteDebitnote(String debitid);

    Integer deleteByIds(Integer[] ids);
}
