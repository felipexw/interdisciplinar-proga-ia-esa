package web.bean.datamodel;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import utili.Col;

public class DefaultDataModel<T extends Object> extends ListDataModel<Object> implements SelectableDataModel<Object>, Serializable {

    public DefaultDataModel(List<Object> objects) {
        super(objects);
    }

    @Override
    public Object getRowKey(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Col.class)) {
                Col anotation = method.getAnnotation(Col.class);
                if (anotation.column() == 0) {
                    try {
                        return method.invoke(object);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(DefaultDataModel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Object getRowData(String string) {
        List<Object> objects = (List<Object>) getWrappedData();

        for (Object object : objects) {
            Class<?> c = object.getClass();
            for (Method method : c.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Col.class)) {
                    Col anotation = method.getAnnotation(Col.class);
                    if (anotation.column() == 0) {
                        try {
                            Object o = method.invoke(object);
                            if (o.equals(string)) {
                                return object;
                            }
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(DefaultDataModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        return null;
    }
}
