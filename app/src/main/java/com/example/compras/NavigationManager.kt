
import android.graphics.Color
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.compras.ComprasFragment
import com.example.compras.R
import com.example.compras.formulario_compras


abstract class NavigationManager{
    companion object{
        private fun placeFragment(fm: FragmentManager, fragment: Fragment){
            val transition = fm.beginTransaction()
            transition.replace(R.id.menu_drawer, fragment) // frame onde está criar artigo
            transition.addToBackStack(null)
            transition.commit()
        }

        fun goToComprasFragment(fm: FragmentManager){
            placeFragment(fm, ComprasFragment())
        }

        fun goToNovoArtigoFragment(fm: FragmentManager){
            placeFragment(fm, formulario_compras())
        }



    }
}