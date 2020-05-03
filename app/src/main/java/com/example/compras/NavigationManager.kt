
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.compras.ComprasFragment
import com.example.compras.R
import com.example.compras.FormularioFragment


abstract class NavigationManager{
    companion object{
        fun goToComprasFragment(fm: FragmentManager){
            placeFragment(fm, ComprasFragment())
        }

        fun goToNovoArtigoFragment(fm: FragmentManager){
            placeFragment(fm, FormularioFragment())
        }

        private fun placeFragment(fm: FragmentManager, fragment: Fragment){
            val transition = fm.beginTransaction()
            transition.replace(R.id.menu_drawer, fragment) // frame onde está criar artigo
            transition.addToBackStack(null)
            transition.commit()
        }

    }
}