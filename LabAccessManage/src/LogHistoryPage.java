import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class LogHistoryPage extends JFrame {

    private JComboBox searchType;
    private JButton searchBtn;
    private JPanel searchPanel;
    private DateSelector dateSelector, timeSelectorStart, timeSelectorEnd;
    private JLabel lblNewLabel;
    private JPanel contentPane;
    private JTable table;
    private JTextField idText;

    private LogDao logDao = new LogDao();

    /**
     * Create the frame.
     */
    public LogHistoryPage() {
        setTitle("Lab Access System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 660, 340);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        Object[] columns = {"No.", "ID", "Name", "Time"};
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        search(logDao.queryLog(null));
        scrollPane.setViewportView(table);

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchType = new JComboBox();
        searchType.addItem("ID");
        searchType.addItem("Date");
        searchType.addItem("Time Range");
        searchType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String item = e.getItem().toString();
                    if (item.equals("ID")) {
                        searchPanel.removeAll();
                        searchPanel.add(searchType, 0);
                        searchPanel.add(lblNewLabel, 1);
                        searchPanel.add(idText, 2);
                        searchPanel.add(searchBtn, 3);
                    } else if (item.equals("Date")) {
                        searchPanel.removeAll();
                        searchPanel.add(searchType, 0);
                        searchPanel.add(dateSelector, 1);
                        searchPanel.add(searchBtn, 2);
                    } else if (item.equals("Time Range")) {
                        searchPanel.removeAll();
                        searchPanel.add(searchType, 0);
                        searchPanel.add(timeSelectorStart, 1);
                        searchPanel.add(timeSelectorEnd, 2);
                        searchPanel.add(searchBtn, 3);
                    }
                    SwingUtilities.updateComponentTreeUI(LogHistoryPage.this);
                }
            }
        });
        searchPanel.add(searchType);
        lblNewLabel = new JLabel("ID");
        searchPanel.add(lblNewLabel);

        idText = new JTextField();
        idText.setColumns(10);
        searchPanel.add(idText);

        dateSelector = new DateSelector(false);
        dateSelector.setSize(150, 25);

        timeSelectorStart = new DateSelector(true);
        timeSelectorStart.setSize(200, 25);

        timeSelectorEnd = new DateSelector(true);
        timeSelectorEnd.setSize(200, 25);

        //Search
        searchBtn = new JButton("Search");
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = searchType.getSelectedItem().toString();
                if (item.equals("ID")) {
                    search(logDao.queryLog(idText.getText()));
                } else if (item.equals("Date")) {
                    search(logDao.queryLogByDate(dateSelector.getText()));
                } else if (item.equals("Time Range")) {
                    search(logDao.queryLogByTimeRange(timeSelectorStart.getText(), timeSelectorEnd.getText()));
                }
            }
        });
        searchPanel.add(searchBtn);
        contentPane.add(searchPanel, BorderLayout.NORTH);
    }

    public void search(List<AccessLog> list) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size(); i++) {
            String[] arr = new String[5];
            arr[0] = list.get(i).getId() + "";
            arr[1] = list.get(i).getUid() + "";
            arr[2] = list.get(i).getName();
            arr[3] = sdf.format(list.get(i).getTime());
            tableModel.addRow(arr);
        }
    }
}
