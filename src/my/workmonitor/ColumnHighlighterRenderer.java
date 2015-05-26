/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.workmonitor;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego
 */
public class ColumnHighlighterRenderer extends DefaultTableCellRenderer {

@Override
public Component getTableCellRendererComponent(JTable table, Object obj,
        boolean isSelected, boolean hasFocus, int row, int column) {

    Component cell = super.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);

    cell.setBackground(Color.YELLOW);

    return cell;
    }
}
