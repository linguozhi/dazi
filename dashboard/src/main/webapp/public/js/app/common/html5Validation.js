/**
 * 用html5的方式进行表单校验，兼容jqBootstrapValidation的使用方式；
 * 可作为jquery的插件使用。
 *
 * e.g.:
 * 		$("input,select,textarea").not("[type=submit]").html5Validate();
 *
 * @author guice_lin@126.com
 * @date 2016-05-31
 */
var Html5Validation;

(function ($) {

	// 配置各种校验属性以及对应的自定义提示信息
	var validationMaps = [
		{
			attribute      : 'required',
			validityKey    : 'valueMissing',
			messageKey     : 'data-validation-required-message',
			defaultMessage : ''
		},

		{
			attribute      : 'pattern',
			validityKey    : 'patternMismatch',
			messageKey     : 'data-validation-pattern-message',
			defaultMessage : ''
		},

		{
			attribute      : 'type=email',
			validityKey    : 'typeMismatch',
			messageKey     : 'data-validation-email-message',
			defaultMessage : ''
		},

		{
			attribute      : 'type=number',
			validityKey    : 'typeMismatch',
			messageKey     : 'data-validation-number-message',
			defaultMessage : ''
		},

		{
			attribute      : 'min',
			validityKey    : 'rangeOverflow',
			messageKey     : 'data-validation-min-message',
			defaultMessage : ''
		},

		{
			attribute      : 'max',
			validityKey    : 'rangeOverflow',
			messageKey     : 'data-validation-max-message',
			defaultMessage : ''
		},

		{
			attribute      : 'minlength',
			validityKey    : 'patternMismatch',
			messageKey     : 'data-validation-minlength-message',
			defaultMessage : '',
			regex          : '^.{${1},}$'
		},

		{
			attribute      : 'maxlength',
			validityKey    : 'patternMismatch',
			messageKey     : 'data-validation-maxlength-message',
			defaultMessage : '',
			regex          : '^.{0,${1}}$'
		}

	];

	Html5Validation = {

		/**
		 * 指定要初始化的input元素
		 */
		init : function ( $inputs ) {

			if ( !$inputs ) return;

			var _self = this;

			$inputs.each( function() {

				_self.attachInput ( this );

				$(this).bind( 'invalid', function (e) {
					var input = e.target;
					input.setCustomValidity('');

					if ( input.validity.valid ) return;

					var message = '';

					for ( var i in validationMaps ) {

						var map = validationMaps[i];

						if ( ! _self.existAttribute( map.attribute, input ) ) continue;

						if ( ! input.validity[ map.validityKey ] ) continue;

						message = $(input).attr( map.messageKey ) || map.defaultMessage;
					}

					message && input.setCustomValidity( message );

				}).bind ( 'input', function (e) {
					e.target.setCustomValidity('');

				});


			});//end each

		},//end init

		attachInput : function ( input ) {

			// attach with maxlength or minlength:
			for ( var i in validationMaps ) {

				var map = validationMaps[i];

				if ( "regex" in map ) {

					var value = $(input).attr( map.attribute );

					if ( !value)  continue ;

					$(input).attr( 'pattern', map.regex.replace( '${1}', value ) );

					var message = $(input).attr( map.messageKey ) || map.defaultMessage;
					message && $(input).attr( 'data-validation-pattern-message', message );
				}
			}
		},

		existAttribute : function ( attribute, element ) {

			if ( /^type=/.test( attribute ) ) {
				return element.type 
					&& attribute.split('=')[1] === element.type.toLowerCase();
			}

			return attribute in element;
		}


	};//end Html5Validation


	/**
	 * 作为jquery的插件加入
	 */
	$.fn.html5Validate = function() {
		Html5Validation.init( $(this) );
	};


})(jQuery);
