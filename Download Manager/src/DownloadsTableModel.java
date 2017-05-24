import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class DownloadsTableModel extends AbstractTableModel implements Observer 
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] columnNames = {"URL","Size","Progress","Status"};
	 private static final Class[] columnClassses = {String.class, String.class, JProgressBar.class, String.class};
	 private ArrayList <Download> downloadlist = new ArrayList<Download>();
	 public void addDownload(Download download)
	 {
		 download.addObserver(this);
		 downloadlist.add(download);
		 
		 fireTableRowsInserted(getColumnCount() - 1, getRowCount() - 1);
	 }
	 public Download getDownload(int row)
	 {
		 return downloadlist.get(row);
	 }
	 
	 public void clearDownload(int row)
	 {
		 downloadlist.remove(row);
		 fireTableRowsDeleted(row, row);
	 }
	 
	 public int getColumnCount()
	 {
		 return columnNames.length;
	 }
	 
	 public String getColumnName(int col)
	 {
		 return columnNames[col];
	 }
	 
	 public Class<?> getColumnClass(int col)
	 {
		 Class[] columnClasses = null;
		try 
		{
			return columnClasses[col];
	    }
		catch (Exception e){}
		return null;
		}
	 
	 public int getRowCount()
	 {
		 return downloadlist.size();
	 }
	 
	 public Object getValues(int row,int col)
	 {
		 Download download = downloadlist.get(row);
		 switch(col)
		 {
		 case 0: return download.getUrl();
		 case 1: int size = download.getSize();
		         return (size==-1) ? "" : Integer.toString(size);
		 case 2: return new Float(download.getProgress());
		 case 3: return Download.STATUSES[download.getStatus()];
		 }
		 return "";
	 } 
		 
		 public void update (Observable o,Object arg)
		 {
			 int index = downloadlist.indexOf(o);
			 fireTableRowsUpdated(index, index);
		 }
		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		public static Class[] getColumnclassses() {
			return columnClassses;
		}
	 } 