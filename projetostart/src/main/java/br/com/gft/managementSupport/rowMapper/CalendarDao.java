package br.com.gft.managementSupport.rowMapper;

import java.util.List;

import br.com.gft.managementSupport.entity.Holiday;
import br.com.gft.managementSupport.gridViews.CalendarView;

public interface CalendarDao {

	CalendarView find(Long id);
	Holiday save (Holiday obj);
	public void delete(Long id);
	public List<CalendarView> findAll();
	public int findTotalHoliday();
}
