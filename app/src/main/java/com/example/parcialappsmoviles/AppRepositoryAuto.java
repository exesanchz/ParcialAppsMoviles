package com.example.parcialappsmoviles;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppRepositoryAuto implements OnAutoResultCallback{
    private AutoDao autoDao;
    private OnResultCallback callback;

    public AppRepositoryAuto (Application application, OnResultCallback context){
        AppDataBase.AppDatabase db = AppDataBase.AppDatabase.getInstance(application);
        autoDao = db.autoDao();
        callback = context;
    }

    public void insertar(final Auto auto){
        autoDao.insertar(auto);
    }

    public void borrar(final Auto auto){
        autoDao.borrar(auto);
    }

    public void actualizar(final Auto auto){
        autoDao.actualizar(auto);
    }

    public void buscar(String id) {
        new BuscarAutoById(autoDao, this).execute(id);
    }

    public List<Auto> buscarTodos() {
        new BuscarAutos(autoDao, this).execute();
        return autoDao.buscarTodos();
    }

    @Override
    public void onResult(List<Auto> autos) {
        Log.d("DEBUG", "Auto found");
        callback.onResult(autos);
    }

    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }

    class BuscarAutos extends AsyncTask<String, Void, List<Auto>> {

        private AutoDao dao;
        private OnAutoResultCallback callback;

        public BuscarAutos(AutoDao dao, OnAutoResultCallback context) {
            this.dao = dao;
            this.callback = context;
        }

        @Override
        protected List<Auto> doInBackground(String... strings) {
            List<Auto> autos = dao.buscarTodos();
            return autos;
        }

        @Override
        protected void onPostExecute(List<Auto> autos) {
            super.onPostExecute(autos);
            callback.onResult(autos);
        }
    }

    class BuscarAutoById extends AsyncTask<String, Void, List<Auto>> {

        private AutoDao dao;
        private OnAutoResultCallback callback;

        public BuscarAutoById(AutoDao dao, OnAutoResultCallback context) {
            this.dao = dao;
            this.callback = context;
        }

        @Override
        protected List<Auto> doInBackground(String... strings) {
            List<Auto> autos = new ArrayList<>();
            return autos;
        }

        @Override
        protected void onPostExecute(List<Auto> autos) {
            super.onPostExecute(autos);
            callback.onResult(autos);
        }
    }
}
