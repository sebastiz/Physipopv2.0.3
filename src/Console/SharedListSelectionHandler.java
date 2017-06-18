package Console;



import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.pmw.tinylog.Logger;

class SharedListSelectionHandler implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) { 
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
       // ArrayList<String> myCol =new ArrayList<String>();

        int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting(); 
       //System.out.println("Event for indexes "
                     // + firstIndex + " - " + lastIndex
                     // + "; isAdjusting is " + isAdjusting
                    // + "; selected indexes:");
       
       
       
       

        if (lsm.isSelectionEmpty()) {
       //     output.append(" <none>");
        } else {
            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    //System.out.println(" " + i);
                }
            }
        }

    }
}

