package soup.qr.ui.magnified

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import soup.qr.R
import soup.qr.core.encoder.BarcodeImage
import soup.qr.databinding.MagnifiedFragmentBinding
import soup.qr.mapper.BarcodeFormatMapper
import soup.qr.model.Barcode
import soup.qr.ui.result.BarcodeResultFragmentArgs
import soup.qr.utils.setOnDebounceClickListener

class BarcodeMagnifiedFragment : Fragment() {

    private val viewModel: BarcodeMagnifiedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MagnifiedFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.barcodeImage.setOnDebounceClickListener {
            findNavController().navigateUp()
        }
        viewModel.uiModel.observe(viewLifecycleOwner, Observer {
            binding.barcodeImage.setBarcodeImage(it)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: BarcodeResultFragmentArgs by navArgs()
        viewModel.onCreated(args.barcode)
    }

    private fun ImageView.setBarcodeImage(barcode: Barcode) {
        setImageBitmap(createBarcodeImage(barcode))
    }

    private fun View.createBarcodeImage(barcode: Barcode): Bitmap? {
        val size = context.resources.getDimensionPixelSize(R.dimen.qr_code_size)
        return BarcodeFormatMapper.zxingFormatOf(barcode.format)
            ?.let(::BarcodeImage)
            ?.create(barcode.rawValue, size)
    }
}