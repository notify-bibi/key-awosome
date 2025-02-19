package de.uka.ilkd.key.speclang;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.uka.ilkd.key.java.Services;
import de.uka.ilkd.key.logic.Term;
import de.uka.ilkd.key.logic.TermServices;
import de.uka.ilkd.key.logic.op.LocationVariable;


/**
 * Heap contexts are various scenarios of what happens to heap variables during PO generation and
 * built-in rule applications, like saving atPre heaps, anonymisation, etc.
 */
public class HeapContext {

    public static List<LocationVariable> getModHeaps(Services services, boolean transaction) {
        List<LocationVariable> result = new ArrayList<>();
        final LocationVariable savedHeap = services.getTypeConverter().getHeapLDT().getSavedHeap();
        for (LocationVariable heap : services.getTypeConverter().getHeapLDT().getAllHeaps()) {
            if (savedHeap == heap && !transaction) {
                continue;
            }
            result.add(heap);
        }
        return result;
    }

    public static Map<LocationVariable, LocationVariable> getBeforeAtPreVars(
            List<LocationVariable> heaps, TermServices services, String contextName) {
        Map<LocationVariable, LocationVariable> result =
            new LinkedHashMap<>();
        for (LocationVariable heap : heaps) {
            final LocationVariable atPreVar = services.getTermBuilder()
                    .locationVariable(heap.name() + contextName, heap.sort(), true);
            result.put(heap, atPreVar);
        }
        return result;
    }

    public static Map<LocationVariable, Term> getAtPres(
            Map<LocationVariable, LocationVariable> atPreVars, Services services) {
        final Map<LocationVariable, Term> result = new LinkedHashMap<>();
        for (LocationVariable heap : services.getTypeConverter().getHeapLDT().getAllHeaps()) {
            final LocationVariable lv = atPreVars.get(heap);
            final Term t = lv == null ? null : services.getTermBuilder().var(lv);
            result.put(heap, t);
        }
        return result;
    }

}
